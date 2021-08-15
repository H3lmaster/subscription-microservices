package com.has.microservices.subscription.processor.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.has.microservices.subscription.processor.client.SubscriptionClient;
import com.has.microservices.subscription.processor.model.Subscription;

@Component
public class SubscriptionConsumer {

	
	private static final Logger log = LoggerFactory.getLogger(SubscriptionConsumer.class);
	
	@Value("${adapter.max-retry}")
	private int maxRetry;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private SubscriptionClient subscriptionClient;
	
	@RabbitListener(queues = "${spring.rabbitmq.template.default-receive-queue}")
	public void receive(Subscription subscription) {
		
		try {
			
			log.info("Subscription request received for email(" + subscription.getEmail() + ") and newsletterid (" + subscription.getNewsletterId() + ")");
			
			Subscription result = subscriptionClient.createSubscription(subscription);
			if ( result != null && result.getId() != null ) {
				log.info("Subscription (" + result.getId() + ") created successfully.");
			}
			
		} catch (Exception e) {
			log.error("Error creating subscription for email " + subscription.getEmail() + ". Sending it back to the queue.");
			subscription.setRetryCount(subscription.getRetryCount() + 1);
			rabbitTemplate.convertAndSend(subscription);
			log.error(e.getMessage());
		}
	}
}
