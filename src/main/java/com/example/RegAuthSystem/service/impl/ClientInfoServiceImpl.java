package com.example.RegAuthSystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.mapper.ClientInfoMapper;
import com.example.RegAuthSystem.service.IClientInfoService;
import com.example.RegAuthSystem.service.dto.ClientInfoDto;
import com.example.orm.entity.ClientInfo;
import com.example.orm.repository.ClientInfoRepository;


@Service
public class ClientInfoServiceImpl implements IClientInfoService{

	@Autowired
	ClientInfoRepository clientInfoRepository;
	@Autowired
	ClientInfoMapper clientInfoMapper;

	public ClientInfoDto findByClientId(Long clientId) {
		return clientInfoRepository.findByClientId(clientId).map(clientInfoMapper::toDto).orElse(null);
	}

	@Override
	public void updateUserProfile(ClientInfoDto clientInfoDto) {
		// TODO Auto-generated method stub
//		ㄏㄛ
//		clientInfoRepository.save(null);
	}
}
