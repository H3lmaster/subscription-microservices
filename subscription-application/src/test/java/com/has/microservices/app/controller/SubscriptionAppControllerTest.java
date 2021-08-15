package com.has.microservices.app.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.has.microservices.app.builder.SubscriptionBuilder;
import com.has.microservices.app.client.SubscriptionClient;
import com.has.microservices.app.model.Subscription;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SubscriptionAppController.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@EnableAutoConfiguration
@ActiveProfiles("test")
public class SubscriptionAppControllerTest {

	
	private static final String PATH = "/app/api/subscription";
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SubscriptionClient subscriptionClient;
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before()
	public void setup()
	{
	   mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	
	@Test
	public void whenGetAllThenStatusIsOk() throws Exception {
		
		this.mockMvc.perform(get(PATH))
					.andExpect(status().isOk());
	}
	
	@Test
	public void whenGetByIdThenStatusIsNotFound() throws Exception {
		
		this.mockMvc.perform(get(PATH + "/1"))
					.andExpect(status().isNotFound());
	}
	
	@Test
	public void whenPostSubscriptionThenStatusIsOk() throws Exception {
		
		Subscription subscription = new SubscriptionBuilder()
											.withEmail("test@test.com")
											.withFirstName("Bob Dylan")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.isConsent(true)
											.build();
				
		this.mockMvc.perform(post(PATH)
								.content(new ObjectMapper().writeValueAsString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
	}

	@Test
	public void whenPostSubscriptionMissingRequiredFieldsThenStatusIsBadRequest() throws Exception {

		Subscription subscription = new SubscriptionBuilder()
											.withEmail("test@test.com")
											.withFirstName("Chris Frasle")
											.withGender("Male")
											.withDateofBirth(new Date())
											.build();
				
		this.mockMvc.perform(post(PATH)
								.content(new ObjectMapper().writeValueAsString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenPostSubscriptionWithInvalidEmailThenStatusIsBadRequest() throws Exception {

		Subscription subscription = new SubscriptionBuilder()
											.withFirstName("Chris Frasle")
											.withEmail("chrisfrasle.com")
											.withGender("Male")
											.withDateofBirth(new Date())
											.withNewsletterId(new Long(1231))
											.build();
				
		this.mockMvc.perform(post(PATH)
								.content(new ObjectMapper().writeValueAsString(subscription))
								.accept(MediaType.APPLICATION_JSON)
								.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isBadRequest());
	}
	
	@Test
	public void whenDeleteThenStatusIsNotFound() throws Exception {
		
		this.mockMvc.perform(delete(PATH + "/1"))
					.andExpect(status().isNotFound());
	}
}
