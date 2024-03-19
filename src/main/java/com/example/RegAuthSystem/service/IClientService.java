package com.example.RegAuthSystem.service;

import java.util.Optional;

import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.orm.entity.Client;

public interface IClientService {

	/**
	 * 從透過Email查詢客戶資料
	 * @param email
	 */
	ClientDto findByEmail(String email);

	/**
	 * 創立新的Client資料
	 * @param email
	 * @param emailExpirationTime
	 */
	ClientDto createClient(String email, Long emailExpirationTime);

	/**
	 * 更新Client的Email RegistrationProgressVerificationCodeExpiryTime
	 * @param clientDto
	 * @param emailExpirationTime
	 */
	void updateClientRegistrationVerificationCodeExpiryTime(ClientDto clientDto, Long emailExpirationTime);

	/**
	 * 透過 RegistrationProgressVerificationCode 尋找Client
	 * @param registrationProgressVerificationCode
	 */
	ClientDto findByRegistrationProgressVerificationCode(String registrationProgressVerificationCode);

	void updateClientRegistrationProgressVerificationCodeExpiryTime(ClientDto clientDto, Long emailExpirationTime);

}
