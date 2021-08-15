package com.has.microservices.subscription.processor.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

	
	private String id;

	private String firstName; 

	private String email;

	private String gender;

	private Date dateOfBirth;

	private Boolean consent;

	private Long newsletterId;
	
	private int retryCount;
}
