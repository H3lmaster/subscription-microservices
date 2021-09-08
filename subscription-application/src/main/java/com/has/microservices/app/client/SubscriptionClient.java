package com.has.microservices.app.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.has.microservices.app.config.FeignClientConfiguration;
import com.has.microservices.app.fallback.SubscriptionClientFallback;
import com.has.microservices.app.model.Subscription;

@FeignClient(
		value = "subscription",
		url = "${subscription.endpoint}",
		configuration = FeignClientConfiguration.class,
		fallback = SubscriptionClientFallback.class
				)
public interface SubscriptionClient {
	
	@GetMapping
	List<Subscription> getSubscriptions();
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Subscription getSubscription(@PathVariable("id") String id);
	
	@PostMapping
	Subscription submitSubscription(Subscription subscription);
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Subscription cancelSubscription(@PathVariable("id") String id);
}
