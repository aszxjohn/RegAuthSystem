package com.example.RegAuthSystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.console.Option;
import com.example.RegAuthSystem.mapper.ClientMapper;
import com.example.RegAuthSystem.service.IClientService;
import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.common.enums.ClientStatusEnum;
import com.example.common.utils.TimeUtil;
import com.example.common.utils.UuidUtil;
import com.example.orm.entity.Client;
import com.example.orm.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientMapper clientMapper;

	/**
	 * 從透過Email查詢客戶資料
	 * 
	 * @param email
	 * @return
	 */
	@Override
	public ClientDto findByEmail(String email) {
		return clientRepository.findByEmail(email).map(clientMapper::toDto).orElse(null);
	}

	/**
	 * 創立新的Client資料
	 * 
	 * @param email
	 * @param emailExpirationTime
	 * @return
	 */
	@Override
	public Optional<Client> createClient(String email, Long emailExpirationTime) {
		Client client = new Client();
		client.setEmail(email);
		client.setStatus(ClientStatusEnum.EMAIL_VERIFIED.getStatus());
		client.setRegistrationVerificationCode(UuidUtil.uuidGenerate());
		client.setRegistrationVerificationCodeExpiryTime(TimeUtil.getExpiredTime(emailExpirationTime));
		client.setPassword("");
		client.setLoginFailCount(0);
		client.setIsLock(false);
		client.setEnableTwoFactor(true);
		Optional<Client> clientOptional = Optional.of(clientRepository.save(client));
		return clientOptional;
	}

	/**
	 * 更新Client的Email RegistrationProgressVerificationCodeExpiryTime
	 * 
	 * @param clientDto
	 * @param emailExpirationTime
	 */
	@Override
	public void updateClientRegistrationVerificationCodeExpiryTime(ClientDto clientDto, Long emailExpirationTime) {
		clientDto.setRegistrationVerificationCode(UuidUtil.uuidGenerate());
		clientDto.setRegistrationVerificationCodeExpiryTime(TimeUtil.getExpiredTime(emailExpirationTime));
		clientRepository.save(clientMapper.toEntity(clientDto));
	}

	/**
	 * 透過 RegistrationProgressVerificationCode 尋找Client
	 * 
	 * @param registrationProgressVerificationCode
	 */
	@Override
	public Optional<Client> findByRegistrationProgressVerificationCode(String registrationProgressVerificationCode) {
		return clientRepository.findByRegistrationProgressVerificationCode(registrationProgressVerificationCode);

	}

	/**
	 * 更新使用者的註冊進度驗證碼
	 * 
	 * @param clientDto
	 * @param emailExpirationTime
	 */
	@Override
	public void updateClientRegistrationProgressVerificationCodeExpiryTime(ClientDto clientDto,
			Long emailExpirationTime) {
		clientDto.setRegistrationProgressVerificationCode(UuidUtil.uuidGenerate());
		clientDto.setRegistrationProgressVerificationCodeExpiryTime(TimeUtil.getExpiredTime(emailExpirationTime));
		clientRepository.save(clientMapper.toEntity(clientDto));
	}

	/**
	 * 透過註冊驗證碼來查詢Client資料
	 */
	@Override
	public ClientDto findByRegistrationVerificationCode(String registrationVerificationCode) {
		return clientRepository.findByRegistrationVerificationCode(registrationVerificationCode)
				.map(clientMapper::toDto).orElse(null);
	}

	@Override
	public boolean updateUserProfile(Client client) {
		try {
			clientRepository.save(client);
			return true;
		} catch (Exception e) {
			log.info("updateUserProfile fail : " + e.getMessage());
			return false;
		}
	}

}
