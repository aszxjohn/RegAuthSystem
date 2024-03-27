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
	Optional<Client> createClient(String email, Long emailExpirationTime);

	/**
	 * 更新Client的Email RegistrationProgressVerificationCodeExpiryTime
	 * @param clientDto
	 * @param emailExpirationTime
	 */
	void updateClientRegistrationVerificationCodeExpiryTime(ClientDto client, Long emailExpirationTime);

	/**
	 * 透過 RegistrationProgressVerificationCode 尋找Client
	 * @param registrationProgressVerificationCode
	 */
	Optional<Client> findByRegistrationProgressVerificationCode(String registrationProgressVerificationCode);

	/**
	 * 更新使用者的註冊進度驗證碼
	 * @param clientDto
	 * @param emailExpirationTime
	 */
	void updateClientRegistrationProgressVerificationCodeExpiryTime(ClientDto client, Long emailExpirationTime);

	/**
	 * 透過客戶的註冊驗證碼查詢Client資料
	 * @param registrationVerificationCode
	 * @return
	 */
	ClientDto findByRegistrationVerificationCode(String registrationVerificationCode);

	boolean updateUserProfile(Client client);

}
