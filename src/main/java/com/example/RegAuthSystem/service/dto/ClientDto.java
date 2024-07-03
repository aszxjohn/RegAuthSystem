package com.example.RegAuthSystem.service.dto;

import java.sql.Timestamp;

import com.example.orm.base.BaseDto;

import lombok.Data;

@Data
public class ClientDto extends BaseDto {

	private static final long serialVersionUID = 1L;

	private Long clientId;

	private String customerNumber;

	private Boolean enableTwoFactor;

	private Boolean isLock;

	private Timestamp isLockedTime;

	private Integer loginFailCount;

	private Timestamp loginSuccessDate;

	private String email;

	private String password;

	private Long registerReviewId;

	private String registrationVerificationCode;

	private Timestamp registrationVerificationCodeExpiryTime;

	private String registrationProgressVerificationCode;

	private Timestamp registrationProgressVerificationCodeExpiryTime;

	private String resetPasswordVerificationCodeEmail;

	private Timestamp resetPasswordVerificationCodeExpiryTime;

	private String setPasswordVerificationCodeEmail;

	private Timestamp setPasswordVerificationCodeExpiryTime;

	private Integer status;

	private String twoFactorCode;

	private Timestamp twoFactorCodeExpiryTime;
	
	private ClientInfoDto clientInfoDto;
		

}