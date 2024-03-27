package com.example.RegAuthSystem.service.dto;

import com.example.orm.base.BaseDto;

import lombok.Data;


@Data
public class UpdateUserProfileDto  extends BaseDto{

	private String clientAddress;

	private String identificationNumber;

	private String phoneNumbe;

}