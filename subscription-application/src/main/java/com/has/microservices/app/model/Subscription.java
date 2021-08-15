package com.has.microservices.app.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscription {

	private String id;

	private String firstName; 

	@Email(message = "Email is invalid")
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
