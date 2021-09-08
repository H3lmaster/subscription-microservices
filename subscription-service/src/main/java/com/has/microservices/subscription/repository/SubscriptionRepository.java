package com.has.microservices.subscription.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.has.microservices.subscription.model.Subscription;

public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, String> {

}
