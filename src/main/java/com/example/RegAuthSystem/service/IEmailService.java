package com.example.RegAuthSystem.service;

import java.util.Collection;
import java.util.Optional;

import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.orm.entity.EmailTemplate;

public interface IEmailService {

	/**
	 * 不使用"信件模板"寄送Email
	 * @param emailSender
	 * @param receivers
	 * @param subject
	 * @param content
	 * @return
	 */
	Boolean sendEmail(String emailSender, Collection<String> receivers, String subject, String content);

	/**
	 * 使用"信件模板"寄送Email
	 * @param emailTemplate
	 * @param associatedApi
	 * @param clientDto
	 * @param emailSender
	 * @param emailRedirectUrl
	 * @return
	 */
	Boolean sendEmailWithTemplateToUser(Optional<EmailTemplate> emailTemplate, String associatedApi, String uuid,
			String email, String emailSender, String emailRedirectUrl);

}
