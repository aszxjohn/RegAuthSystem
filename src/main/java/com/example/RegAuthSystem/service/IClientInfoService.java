package com.example.RegAuthSystem.service;

import java.util.Optional;

import com.example.orm.entity.ClientInfo;

public interface IClientInfoService {

	Optional<ClientInfo> findByClientId(Long clientId);


}
