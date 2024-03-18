package com.example.RegAuthSystem.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.service.IEmailTemplateService;
import com.example.orm.entity.EmailTemplate;
import com.example.orm.repository.EmailTemplateRepository;

@Service
public class EmailTemplateServiceImpl implements IEmailTemplateService{
	@Autowired
	private EmailTemplateRepository emailTemplateRepository;
	

	/**
	 * 透過associatedApi尋找信件模板
	 * @param associatedApi
	 * @return
	 */
	@Override
	public Optional<EmailTemplate> findByAssociatedApi(String associatedApi) {
		return emailTemplateRepository.findByAssociatedApi(associatedApi);
	}
}
