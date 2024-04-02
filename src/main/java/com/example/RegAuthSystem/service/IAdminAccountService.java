package com.example.RegAuthSystem.service;

import java.util.Optional;

import com.example.orm.entity.AdminAccount;

public interface IAdminAccountService {

	Optional<AdminAccount> getAccount();

}
