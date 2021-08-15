package com.has.microservices.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.has.microservices.email.model.Subscription;
import com.has.microservices.email.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
    
	@Override
	public void sendEmail(Subscription subscription) throws Exception {
		logger.info("Email to (" + subscription.getEmail() + ") sent");
	}

}
