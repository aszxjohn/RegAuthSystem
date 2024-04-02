package com.example.RegAuthSystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.service.IAdminAccountService;
import com.example.orm.entity.AdminAccount;
import com.example.orm.repository.AdminAccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminAccountServiceImpl implements IAdminAccountService {

	@Autowired
	private AdminAccountRepository adminAccountRepository;
	
	@Override
	public Optional<AdminAccount> getAccount( ) {
		return adminAccountRepository.findById(1L);
		
	}

}
