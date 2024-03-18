package com.example.RegAuthSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.service.ISystemParameterSettingService;
import com.example.orm.entity.SystemParameterSetting;
import com.example.orm.repository.SystemParameterSettingRepository;

@Service
public class SystemParameterSettingServiceImpl implements ISystemParameterSettingService{
	
	
	@Autowired
	private SystemParameterSettingRepository systemParameterSettingRepository;

	/**
	 * 尋找"信件過期時間"的設定
	 * @return
	 */
	@Override
	public Long findEmailExpirationTime() {
		return systemParameterSettingRepository.findBySystemGroupAndSystemKey("email", "email_expiration_time")
	            .map(SystemParameterSetting::getSystemValue) // 將Optional<SystemParameterSetting>轉換為Optional<String>
	            .map(Long::parseLong) // 將Optional<String>轉換為Optional<Long>
	            .orElse(0L); // 如果結果是空的，則使用預設值
	}

	/**
	 * 尋找"寄件者"的設定
	 * @return
	 */
	@Override
	public String findEmailSender() {
		return systemParameterSettingRepository.findBySystemGroupAndSystemKey("email", "email_sender")
	            .map(SystemParameterSetting::getSystemValue) // 將Optional<SystemParameterSetting>轉換為Optional<String>
	            .orElse("Shirai <shiraiforwork@gmail.com>"); // 如果結果是空的，則使用預設值
	}

	/**
	 * 透過EmailType尋找用來取代"信件模板"中特定文字的參數
	 * @return
	 */
	@Override
	public String findParametersForEmailType(String emailTpye) {
		return systemParameterSettingRepository.findBySystemGroupAndSystemKey("email", emailTpye)
	            .map(SystemParameterSetting::getSystemValue) // 將Optional<SystemParameterSetting>轉換為Optional<String>
	            .orElse("URL 產生錯誤，請聯繫客服"); // 如果結果是空的，則使用預設值
	}
	
	
	
}
