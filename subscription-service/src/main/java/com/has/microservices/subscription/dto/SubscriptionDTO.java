package com.has.microservices.subscription.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubscriptionDTO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String firstName; 
	
	@Email
    @NotBlank(message = "email is mandatory")
	private String email;
	
	private String gender;
	
    @NotNull(message = "dateOfBirth is mandatory")
	private Date dateOfBirth;
	
    @NotNull(message = "consent is mandatory")
	private Boolean consent;
	
    @NotNull(message = "newsletterId is mandatory")
	private Long newsletterId;


}
