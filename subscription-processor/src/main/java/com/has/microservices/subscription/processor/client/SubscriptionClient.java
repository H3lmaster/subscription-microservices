package com.has.microservices.subscription.processor.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import com.has.microservices.subscription.processor.config.FeignClientConfiguration;
import com.has.microservices.subscription.processor.fallback.SubscriptionClientFallback;
import com.has.microservices.subscription.processor.model.Subscription;


@FeignClient(
		value = "subscription",
		url = "${subscription-service.endpoint}",
		configuration = FeignClientConfiguration.class, 
		fallback = SubscriptionClientFallback.class
				)
public interface SubscriptionClient {

	@PostMapping(value = "/create")
	Subscription createSubscription(Subscription subscription);
}
