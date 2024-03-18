package com.example.RegAuthSystem.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.apache.commons.lang3.function.Failable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.RegAuthSystem.service.IEmailService;
import com.example.RegAuthSystem.service.ISystemParameterSettingService;
import com.example.RegAuthSystem.service.dto.ClientDto;
import com.example.orm.entity.EmailTemplate;
import com.example.orm.repository.EmailTemplateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmailServiceImpl implements IEmailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	/**
	 * 使用"信件模板"寄送Email
	 * @param emailTemplate
	 * @param associatedApi
	 * @param clientDto
	 * @param emailSender
	 * @param emailRedirectUrl
	 * @return
	 */
	@Override
	public Boolean sendEmailWithTemplateToUser(Optional<EmailTemplate> emailTemplate, String associatedApi, ClientDto clientDto, String emailSender, String emailRedirectUrl){
		try {
			emailTemplate.get().setContent(this.replaceTemplateContent(associatedApi, 
					emailTemplate.get().getContent(), emailRedirectUrl, clientDto.getRegistrationProgressVerificationCode()));
				
			Collection<String> receivers = new ArrayList<>();
			receivers.add(clientDto.getEmail());
				
			this.sendEmail(emailSender, receivers, emailTemplate.get().getSubject(), emailTemplate.get().getContent());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			log.info("initiateRegistrationWithEmail is fail. Because:  " + e.getMessage());
			return false;
		}
		
				
	}
	

	/**
	 * 選擇模板及替代用文字
	 * @param correspondingApi
	 * @param emailBody
	 * @return
	 */
	protected String replaceTemplateContent(String correspondingApi, String emailBody, String altText, String GUID) {
		String result = "Url異常，請聯繫客服";
        switch (correspondingApi) {
	        case "register_user":	// 註冊信件
	        	result = emailBody.replaceAll( "\\{\\{URL-Register\\}\\}", altText + GUID);
	        	break;
        }
        
		return result;
	}
	
	/**
	 * 不使用"信件模板"寄送Email
	 * @param emailSender
	 * @param receivers
	 * @param subject
	 * @param content
	 * @return
	 */
	@Override
	public Boolean sendEmail(String emailSender, Collection<String> receivers, String subject, String content) {
		try {
			 SimpleMailMessage message = new SimpleMailMessage();
		        message.setTo(receivers.toArray(new String[receivers.size()]));
		        message.setFrom(emailSender);
		        message.setSubject(subject);
		        message.setText(content);
		        mailSender.send(message);
			 return true;
		} catch (Exception e) {
			log.info("Send Email Fail, Because : " + e.getMessage());
			return false;
		}
       
    }
	
	
}
