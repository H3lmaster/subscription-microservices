package com.has.microservices.subscription.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {
	
	@Id
	private String id;
	
	private String firstName; 
	
	private String email;
	
	private String gender;
	
	private Date dateOfBirth;
	
	private Boolean consent;
	
	private Long newsletterId;

}
