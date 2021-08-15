package com.has.microservices.email.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.has.microservices.email.model.Subscription;
import com.has.microservices.email.service.EmailService;

@Component
public class EmailConsumer {

	private static final Logger log = LoggerFactory.getLogger(EmailConsumer.class);
	
	@Value("${adapter.max-retry}")
	private int maxRetry;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private EmailService emailService;
	
	@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
	public void receive(Subscription subscription) {
		
		try {
			
			if (subscription.getIsRetry() != null && subscription.getIsRetry()) {
				log.info("Retrying sending email for email address: " + subscription.getEmail());
				subscription.setRetryCount(subscription.getRetryCount() + 1);
			} else {
				log.info("Email request received for email address: " + subscription.getEmail());
			}
			
			if ( subscription.getRetryCount() < maxRetry ) {
				this.emailService.sendEmail(subscription);
			} else {
				log.error("Discarding email request for (" + subscription.getEmail() + ") due exceeding retry count.");
			}
			
		} catch (Exception e) {
				log.error("Sending the email request for (" + subscription.getEmail() + ") back to the queue..." );
				subscription.setIsRetry(true);
				rabbitTemplate.convertAndSend(subscription);
		}
	}
}
