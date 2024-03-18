package com.example.RegAuthSystem.service;

import java.util.Optional;

import com.example.orm.entity.EmailTemplate;

public interface IEmailTemplateService {

	/**
	 * 透過associatedApi尋找信件模板
	 * @param associatedApi
	 * @return
	 */
	Optional<EmailTemplate> findByAssociatedApi(String associatedApi);

}
