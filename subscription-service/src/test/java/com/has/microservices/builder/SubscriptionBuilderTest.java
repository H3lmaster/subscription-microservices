package com.has.microservices.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.has.microservices.subscription.builder.SubscriptionBuilder;
import com.has.microservices.subscription.dto.SubscriptionDTO;
import com.has.microservices.subscription.model.Subscription;

@SpringBootTest
public class SubscriptionBuilderTest {

	private static final String email = "tester@email.com";
	private static final String firstName = "Tom curren";
	private static final String gender = "Male";
	private static final Date dateOfBirth = new Date();
	private static final Long newsletterId = 122L;
	private static final Boolean isConsent = true;
	private static final String id = "adfasf-thaddfb";
	
	@Test
	public void whenBuildFromDTOThenAllFieldsAreEquals() {
		
		Subscription subscription = new SubscriptionBuilder()
				.withId(id)
				.withEmail(email)
				.withFirstName(firstName)
				.withGender(gender)
				.withDateofBirth(dateOfBirth)
				.withNewsletterId(newsletterId)
				.isConsent(isConsent)
				.build();
		
		assertEquals(email, subscription.getEmail());
		assertEquals(firstName, subscription.getFirstName());
		assertEquals(gender, subscription.getGender());
		assertEquals(dateOfBirth, subscription.getDateOfBirth());
		assertEquals(newsletterId, subscription.getNewsletterId());
		assertEquals(isConsent, subscription.getConsent());
		assertEquals(id, subscription.getId());
	}
	
	@Test
	public void whenBuildFromBuilderThenAllFieldsArePresent() {
		
		SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
		subscriptionDTO.setConsent(isConsent);
		subscriptionDTO.setDateOfBirth(dateOfBirth);
		subscriptionDTO.setEmail(email);
		subscriptionDTO.setFirstName(firstName);
		subscriptionDTO.setGender(gender);
		subscriptionDTO.setNewsletterId(newsletterId);
		subscriptionDTO.setId(id);
		
		Subscription subscription = new SubscriptionBuilder().buildFromDTO(subscriptionDTO);

		assertEquals(email, subscription.getEmail());
		assertEquals(firstName, subscription.getFirstName());
		assertEquals(gender, subscription.getGender());
		assertEquals(dateOfBirth, subscription.getDateOfBirth());
		assertEquals(newsletterId, subscription.getNewsletterId());
		assertEquals(isConsent, subscription.getConsent());
		assertEquals(id, subscription.getId());
	}
}
