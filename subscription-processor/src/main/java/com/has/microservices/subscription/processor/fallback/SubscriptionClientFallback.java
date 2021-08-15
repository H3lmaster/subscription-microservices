package com.has.microservices.subscription.processor.fallback;

import com.has.microservices.subscription.processor.client.SubscriptionClient;
import com.has.microservices.subscription.processor.model.Subscription;

public class SubscriptionClientFallback implements SubscriptionClient  {

	@Override
	public Subscription createSubscription(Subscription subscription) {
		return null;
	}

}
