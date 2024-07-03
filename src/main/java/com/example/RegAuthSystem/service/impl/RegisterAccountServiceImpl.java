package com.example.RegAuthSystem.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.mapper.ClientMapper;
import com.example.RegAuthSystem.service.IClientInfoService;
import com.example.RegAuthSystem.service.IClientService;
import com.example.RegAuthSystem.service.IEmailService;
import com.example.RegAuthSystem.service.IRegisterAccountService;
import com.example.RegAuthSystem.service.ISystemParameterSettingService;
import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.example.RegAuthSystem.service.dto.UpdateUserProfileDto;
import com.example.common.config.HttpBody;
import com.example.common.config.MessageCode;
import com.example.common.config.ResponseResult;
import com.example.common.enums.ClientStatusEnum;
import com.example.orm.entity.Client;
import com.example.orm.entity.ClientInfo;
import com.example.orm.entity.EmailTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RegisterAccountServiceImpl implements IRegisterAccountService {

	@Autowired
	private IClientService clientService;
	@Autowired
	private ISystemParameterSettingService systemParameterSettingService;
	@Autowired
	private IEmailService emailService;
	@Autowired
	private EmailTemplateServiceImpl emailTemplateServiceImpl;
	@Autowired
	private ClientMapper clientMapper;

	/**
	 * 註冊新用戶和錯過驗證信的已註冊的用戶 依照帳號的狀態去處理對應的行為
	 * 
	 * @param clientDto
	 * @return
	 */
	@Override
	public ResponseEntity<Object> registerOrValidateUser(ClientDto clientDto) {
		Timestamp currentTime = Timestamp.from(Instant.now());
		String associatedApi = "register_user";
		Long emailExpirationTime = systemParameterSettingService.findEmailExpirationTime();
		Client client = clientMapper.toEntity(clientDto);
		// 初次登記會寄出驗證信件
		if (clientDto.getStatus() == ClientStatusEnum.NEW_REGISTRATION.getStatus()) {
			Client newClient = clientService.createClient(client.getEmail(), emailExpirationTime).orElse(null);
			return this.sendVerificationEmail(newClient.getEmail(), newClient.getRegistrationVerificationCode(),
					associatedApi) ? ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, null))
							: ResponseResult.failed(
									HttpBody.build(MessageCode.FAILED, "initiateRegistrationWithEmail is fail."));
		}

		// 已經寄出過驗證信件，上次的驗證信在未過期狀況下會回傳 403 Error
		if (clientDto.getStatus() == ClientStatusEnum.EMAIL_VERIFIED.getStatus()
				&& currentTime.before(clientDto.getRegistrationVerificationCodeExpiryTime())) {
			return ResponseResult.forbidden(HttpBody.build(MessageCode.Forbidden,
					"The last verification letter is still valid, so cancel sending again."));
		}

		// 已經寄出過驗證信件，且上次的驗證信以過期，將更新過期時間與UUID重新寄信
		if (clientDto != null && clientDto.getStatus().intValue() == ClientStatusEnum.EMAIL_VERIFIED.getStatus()
				&& currentTime.after(clientDto.getRegistrationVerificationCodeExpiryTime())) {
			clientService.updateClientRegistrationVerificationCodeExpiryTime(clientDto, emailExpirationTime);
			return this.sendVerificationEmail(clientDto.getEmail(), clientDto.getRegistrationVerificationCode(),
					associatedApi) ? ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, null))
							: ResponseResult.failed(HttpBody.build(MessageCode.FAILED,
									"updateExpiredVerificationCodeAndResendEmail is fail."));
		}

		// 如果帳號以存在且狀態在 20(BASIC_INFO_SUBMITTED)以上 代表帳號有註冊或已在使用
		if ((clientDto != null) && (clientDto.getStatus() >= ClientStatusEnum.BASIC_INFO_SUBMITTED.getStatus())) {
			return ResponseResult.validateArgsFailed(HttpBody.build(MessageCode.ACCOUNT_EXISTS,
					"The letter has entered the review stage. Please wait patiently."));
		}

		// 如果走到這邊就代表有出現沒想過的邊際
		return ResponseResult.failed(HttpBody.build(MessageCode.FAILED, "Please contact customer service"));

	}

	/**
	 * 寄送註冊驗證信
	 * 
	 * @param clientDto
	 * @param associatedApi
	 * @return
	 */
	private Boolean sendVerificationEmail(String email, String uuid, String associatedApi) {
		String emailSender = systemParameterSettingService.findEmailSender();
		String emailRedirectUrl = systemParameterSettingService
				.findParametersForEmailType("email_type_" + associatedApi);
		Optional<EmailTemplate> emailTemplate = emailTemplateServiceImpl.findByAssociatedApi(associatedApi);
		return emailService.sendEmailWithTemplateToUser(emailTemplate, associatedApi, email, uuid, emailSender,
				emailRedirectUrl);
	}

	/**
	 * 註冊查詢進度
	 */
	@Override
	public ResponseEntity<Object> checkUserRegistrationProgress(String registrationProgressVerificationCode) {
		return clientService.findByRegistrationProgressVerificationCode(registrationProgressVerificationCode)
				.<ResponseEntity<Object>>map(client -> {

					Timestamp currentTime = Timestamp.from(Instant.now());
					if (!client.getRegistrationProgressVerificationCode().isEmpty()
							&& currentTime.before(client.getResetPasswordVerificationCodeExpiryTime())) {
						return ResponseResult
								.validateArgsFailed(HttpBody.build(MessageCode.VERIFY_EMAIL_STILL_VALID, null));
					}

					ClientStatusEnum status = ClientStatusEnum.getClientStatusEnum(client.getStatus());
					switch (status) {
						case NEW_REGISTRATION:
						case EMAIL_VERIFIED:
						case BASIC_INFO_SUBMITTED:
						case ASSISTANT_REVIEWED:
						case STAFF_REVIEWED:
						case MANAGER_REVIEWED:
						case SUSPENDED:
						case BANNED:
							return ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, status.getDescription()));
						default:
							return ResponseResult.failed(HttpBody.build(MessageCode.FAILED, null));
					}

				}).orElse(ResponseResult.validateArgsFailed(HttpBody.build(MessageCode.ACCOUNT_DOES_NOT_EXIST, null)));
	}

	/**
	 * 使用者會透過Email取得"進度查詢的驗證碼"
	 */
	@Override
	public ResponseEntity<Object> getRegistrationProgress(ClientDto clientDto) {
		String associatedApi = "registration_progress";
		Timestamp currentTime = Timestamp.from(Instant.now());
		Long emailExpirationTime = systemParameterSettingService.findEmailExpirationTime();

		if (clientDto == null) {
			return ResponseResult.validateArgsFailed(HttpBody.build(MessageCode.ACCOUNT_DOES_NOT_EXIST, null));
		}

		if (clientDto.getRegistrationProgressVerificationCode() != null
				&& currentTime.before(clientDto.getRegistrationProgressVerificationCodeExpiryTime())) {
			return ResponseResult.validateArgsFailed(HttpBody.build(MessageCode.LAST_VERIFICATION_CODE_VALID, null));

		}
		clientService.updateClientRegistrationProgressVerificationCodeExpiryTime(clientDto, emailExpirationTime);
		return this.sendVerificationEmail(clientDto.getEmail(), clientDto.getRegistrationProgressVerificationCode(),
				associatedApi) ? ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, null))
						: ResponseResult.failed(HttpBody.build(MessageCode.FAILED, "getRegistrationProgress is fail."));

	}

	@Override
	public ResponseEntity<Object> updateUserProfile(ClientDto clientDto, UpdateUserProfileDto updateUserProfileDto) {
		Client client = clientMapper.toEntity(clientDto);
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setClientAddress(updateUserProfileDto.getClientAddress());
		clientInfo.setIdentificationNumber(updateUserProfileDto.getIdentificationNumber());
		clientInfo.setPhoneNumbe(updateUserProfileDto.getPhoneNumbe());
		client.setClientInfo(clientInfo);
		return clientService.updateUserProfile(client)
				? ResponseResult.ok(HttpBody.build(MessageCode.SUCCESS, null))
				: ResponseResult.failed(HttpBody.build(MessageCode.FAILED, "updateUserProfile is fail."));
	}

}
