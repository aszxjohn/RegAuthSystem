package com.example.RegAuthSystem.service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationProgressRequestDto {

	@Email
	@NotNull
	private String email;
}
