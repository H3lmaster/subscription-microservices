package com.has.microservices.email.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4742430315666765907L;

	private String id;
	
	private String firstName; 
	
	private String email;
	
	private String gender;
	
	private Date dateOfBirth;
	
	private Boolean consent;
	
	private Long newsletterId;
	
	private Boolean isRetry;
	
	private int retryCount;

}