package com.example.RegAuthSystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.mapper.ClientInfoMapper;
import com.example.RegAuthSystem.service.IClientInfoService;
import com.example.orm.entity.ClientInfo;
import com.example.orm.repository.ClientInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientInfoServiceImpl implements IClientInfoService{

	@Autowired
	ClientInfoRepository clientInfoRepository;
	@Autowired
	ClientInfoMapper clientInfoMapper;

	@Override
	public Optional<ClientInfo> findByClientId(Long clientId) {
		return clientInfoRepository.findByClient_ClientId(clientId);
	}


}
