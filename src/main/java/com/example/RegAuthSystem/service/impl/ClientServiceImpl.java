package com.example.RegAuthSystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.mapper.ClientMapper;
import com.example.RegAuthSystem.service.IClientService;
import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.common.enums.ClientStatusEnum;
import com.example.common.utils.TimeUtil;
import com.example.common.utils.UuidUtil;
import com.example.orm.entity.Client;
import com.example.orm.repository.ClientRepository;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private ClientRepository clientRepository;
	
	
	@Autowired
	private ClientMapper clientMapper;
	
	/**
	 * 從透過Email查詢客戶資料
	 * @param email
	 * @return
	 */
	@Override
	public ClientDto findByEmail(String email) {
	    return clientRepository.findByEmail(email)
	            .map(clientMapper::toDto)
	            .orElseGet(() -> {
	                ClientDto clientDto = new ClientDto();
	                clientDto.setEmail(email);
	                clientDto.setStatus(ClientStatusEnum.NEW_REGISTRATION.getStatus());
	                return clientDto;
	            });
	}

	/**
	 * 創立新的Client資料
	 * @param email
	 * @param emailExpirationTime
	 * @return
	 */
	@Override
	public ClientDto createClient(String email, Long emailExpirationTime) {
		Client client = new Client();
		client.setEmail(email);
		client.setStatus(ClientStatusEnum.EMAIL_VERIFIED.getStatus());
		client.setRegistrationVerificationCode(UuidUtil.uuidGenerate());
		client.setRegistrationVerificationCodeExpiryTime(TimeUtil.getExpiredTime(emailExpirationTime));
		client.setPassword("");
		client.setLoginFailCount(0);
		client.setIsLock(false);
		client.setEnableTwoFactor(true);
		client = clientRepository.save(client);
		return clientMapper.toDto(client);
	}

	/**
	 * 更新Client的Email RegistrationProgressVerificationCodeExpiryTime
	 * @param clientDto
	 * @param emailExpirationTime
	 * @return
	 */
	@Override
	public ClientDto updateClientRegistrationVerificationCodeExpiryTime(ClientDto clientDto, Long emailExpirationTime) {
		clientDto.setRegistrationVerificationCode(UuidUtil.uuidGenerate());
		clientDto.setRegistrationVerificationCodeExpiryTime(TimeUtil.getExpiredTime(emailExpirationTime));
		return clientMapper.toDto(clientRepository.save(clientMapper.toEntity(clientDto)));
	}

	/**
	 * 透過 RegistrationProgressVerificationCode 尋找Client
	 * @param registrationProgressVerificationCode
	 * @return
	 */
	@Override
	public ClientDto findByRegistrationProgressVerificationCode(String registrationProgressVerificationCode) {
		return clientRepository.findByRegistrationProgressVerificationCode(registrationProgressVerificationCode)
				.map(clientMapper :: toDto)
				.orElse(null);
		           
	}

}
