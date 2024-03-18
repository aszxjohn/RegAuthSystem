package com.example.RegAuthSystem.service.dto;

import java.sql.Timestamp;

import com.example.common.enums.ClientStatusEnum;
import com.example.orm.base.BaseDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
public class ClientDto  extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private Long clientId;

	private Long clientInfoId;

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

}