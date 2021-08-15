package com.has.microservices.app.fallback;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.has.microservices.app.client.SubscriptionClient;
import com.has.microservices.app.model.Subscription;

@Component
public class SubscriptionClientFallback implements SubscriptionClient {

	@Override
	public List<Subscription> getSubscriptions() {
		return Collections.emptyList();
	}

	@Override
	public Subscription getSubscription(@PathVariable("id") Long id) {
		return null;
	}

	@Override
	public Subscription submitSubscription(@Valid @RequestBody Subscription subscription) {
		return null;
	}

	@Override
	public Subscription cancelSubscription(@PathVariable("id") Long id) {
		return null;
	}

}
