package com.example.RegAuthSystem.service;

import org.springframework.http.ResponseEntity;

import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.google.protobuf.ByteString.Output;

public interface IRegisterAccountService {

	/**
	 * 註冊新用戶和錯過驗證信的已註冊的用戶
	 * 依照帳號的狀態去處理對應的行為
	 * @param clientDto
	 * @return 
	 */
	ResponseEntity<Object> registerOrValidateUser(ClientDto clientDto);

	/**
	 * 透過verifyCode來查詢使用者個註冊進度
	 * @param verifyCode
	 * @return
	 */
	ResponseEntity<Object> checkUserRegistrationProgress(String verifyCode);

	/**
	 * 使用者會透過Email取得"進度查詢的驗證碼"
	 * @param clientDto
	 * @return
	 */
	ResponseEntity<Object> getRegistrationProgress(ClientDto clientDto);

	ResponseEntity<Object> updateUserProfile(ClientDto clientDto, ClientInfoDto clientInfoDto);

}
