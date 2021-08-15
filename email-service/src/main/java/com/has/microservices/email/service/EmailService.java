package com.has.microservices.email.service;

import com.has.microservices.email.model.Subscription;

public interface EmailService {

	public void sendEmail(Subscription subscription) throws Exception;
}
