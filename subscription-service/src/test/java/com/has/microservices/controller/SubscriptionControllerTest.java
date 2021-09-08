package com.has.microservices.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.has.microservices.subscription.builder.SubscriptionBuilder;
import com.has.microservices.subscription.controller.SubscriptionController;
import com.has.microservices.subscription.model.Subscription;
import com.has.microservices.subscription.repository.SubscriptionRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionController.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles("test")
public class SubscriptionControllerTest {

	private static final String PATH = "/api/subscription";
	
	private static final String CREATE_PATH = "/api/subscription/create";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SubscriptionRepository subscriptionRepository;
	
	@MockBean
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before()
	public void setup()
	{
	    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void whenGetAllThenStatusIsOk() throws Exception {
		
		when(subscriptionRepository.findAll()).thenReturn(new ArrayList<Subscription>());
		
		this.mockMvc.perform(get(PATH))
					.andExpect(status().isOk());
	}
	
	@Test
	public void whenGetByUnkownIdThenStatusIsNotFound() throws Exception {
		when(subscriptionRepository.findById("0")).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get(PATH + "/0"))
					.andExpect(status().isNotFound());
	}
	
	@Test
	public void whenPostSubscriptionThenStatusIsOk() throws Exception {
		
		Subscription subscription = new SubscriptionBuilder()
											.withId("adfafadf-eqrfadf")
											.withEmail("test@test.com")
											.withFirstName("Tom Curren")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.isConsent(true)
											.build();
				
		when(subscriptionRepository.save(subscription)).thenReturn(subscription);
		
		this.mockMvc.perform(post(PATH)
								.content(objectAsJsonString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}
	
	@Test
	public void whenPostSubscriptionMissingRequiredFieldsThenStatusIsBadRequest() throws Exception {

		Subscription subscription = new SubscriptionBuilder()
											.withFirstName("Hans Tim")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.build();
				
		when(subscriptionRepository.save(subscription)).thenReturn(subscription);
		
		this.mockMvc.perform(post(PATH)
								.content(objectAsJsonString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenPostCreateSubscriptionThenStatusIsOk() throws Exception {
		
		Subscription subscription = new SubscriptionBuilder()
											.withId("faredfa-adfadfas")
											.withEmail("test@test.com")
											.withFirstName("Tom Curren")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.isConsent(true)
											.build();
				
		when(subscriptionRepository.save(subscription)).thenReturn(subscription);
		
		this.mockMvc.perform(post(CREATE_PATH)
								.content(objectAsJsonString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isCreated());
	}
	
	@Test
	public void whenPostCreateSubscriptionMissingRequiredFieldsThenStatusIsBadRequest() throws Exception {

		Subscription subscription = new SubscriptionBuilder()
											.withFirstName("Hans Tim")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.build();
				
		when(subscriptionRepository.save(subscription)).thenReturn(subscription);
		
		this.mockMvc.perform(post(CREATE_PATH)
								.content(objectAsJsonString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenDeleteThenStatusIsOk() throws Exception {
		when(subscriptionRepository.findById("1"))
			.thenReturn(Optional.of(new SubscriptionBuilder().withId("fdasdf-adfas")
					.build()));
		
		this.mockMvc.perform(delete(PATH + "/1"))
					.andExpect(status().isOk());
	}
	
	@Test
	public void whenDeleteByUnkownIdThenStatusIsNotFound() throws Exception {
		when(subscriptionRepository.findById("1")).thenReturn(Optional.empty());
		
		this.mockMvc.perform(delete(PATH + "/1"))
					.andExpect(status().isNotFound());
	}
	
	private String objectAsJsonString(Object value) {
		
		String objectAsJsonString = "";
		
		try {
			objectAsJsonString = new ObjectMapper().writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new AssertionError("Error during serialization");
		}
		
		return objectAsJsonString;
	}
	
}
