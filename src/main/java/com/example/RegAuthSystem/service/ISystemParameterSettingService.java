package com.example.RegAuthSystem.service;

public interface ISystemParameterSettingService {

	/**
	 * 尋找"信件過期時間"的設定
	 * @return
	 */
	Long findEmailExpirationTime();

	/**
	 * 尋找"寄件者"的設定
	 * @return
	 */
	String findEmailSender();

	/**
	 * 透過EmailType尋找用來取代"信件模板"中特定文字的參數
	 * @return
	 */
	String findParametersForEmailType(String emailTpye);

}
