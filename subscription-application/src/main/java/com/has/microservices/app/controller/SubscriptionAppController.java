package com.has.microservices.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.has.microservices.app.client.SubscriptionClient;
import com.has.microservices.app.model.Subscription;

@RestController
@RequestMapping("/app/api/subscription")
public class SubscriptionAppController {

	@Autowired
	private SubscriptionClient subscriptionClient;
	
	@GetMapping
	public ResponseEntity<List<Subscription>> getAll() {
		List<Subscription> subscriptions = subscriptionClient.getSubscriptions();
		return ResponseEntity.ok(subscriptions);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Subscription> getSubscription(Long id) {
		Subscription subscription = subscriptionClient.getSubscription(id);
		
		if ( subscription == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(subscription);
	}
	
	@PostMapping
	public ResponseEntity<Subscription> submitSubscription(@Valid @RequestBody Subscription subscription) {
		Subscription result = subscriptionClient.submitSubscription(subscription);
		
		if ( subscription == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Subscription> cancelSubscription(@PathVariable Long id) {
		Subscription subscription = subscriptionClient.cancelSubscription(id);
		
		if ( subscription == null ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(subscription);
	}
}
