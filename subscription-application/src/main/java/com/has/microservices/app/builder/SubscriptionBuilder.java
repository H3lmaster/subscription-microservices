package com.has.microservices.app.builder;

import java.util.Date;

import com.has.microservices.app.model.Subscription;

public class SubscriptionBuilder {
	
	private String id;
	private String firstName; 
	private String email;
	private String gender;
	private Date dateOfBirth;
	private Boolean consent;
	private Long newsletterId;
	
	public SubscriptionBuilder() {
		
	}
	
	public SubscriptionBuilder withId(String id) {
		this.id = id;
		return this;
	}
	
	public SubscriptionBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public SubscriptionBuilder withEmail(String email) {
		this.email = email;
		return this;
	}
	
	public SubscriptionBuilder withGender(String gender) {
		this.gender = gender;
		return this;
	}
	
	public SubscriptionBuilder withDateofBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}
	
	public SubscriptionBuilder isConsent(Boolean consent) {
		this.consent = consent;
		return this;
	}
	
	public SubscriptionBuilder withNewsletterId(Long newsletterId) {
		this.newsletterId = newsletterId;
		return this;
	}
	
	public Subscription build() {
		
		Subscription subscription = new Subscription();
		subscription.setId(this.id);
		subscription.setFirstName(this.firstName);
		subscription.setEmail(this.email);
		subscription.setGender(this.gender);
		subscription.setDateOfBirth(this.dateOfBirth);
		subscription.setConsent(this.consent);
		subscription.setNewsletterId(this.newsletterId);
		
		return subscription;
	}

}
