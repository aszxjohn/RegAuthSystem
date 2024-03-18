package com.example.RegAuthSystem.service.dto;


import com.example.orm.base.BaseDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserDto extends BaseDto {
	private static final long serialVersionUID = 1L;

	@Email
	@NotNull
	private String email;


}