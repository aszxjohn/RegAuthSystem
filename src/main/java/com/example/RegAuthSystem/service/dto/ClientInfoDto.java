package com.example.RegAuthSystem.service.dto;

import com.example.orm.base.BaseDto;

import lombok.Data;


@Data
public class ClientInfoDto  extends BaseDto{


	private long clientInfoId;

	private String clientAddress;

	private String identificationNumber;

	private String phoneNumbe;

	private ClientDto clientDto;
}