//package com.has.microservices.email.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.has.microservices.email.model.Subscription;
//
//@RunWith(SpringRunner.class)
//@AutoConfigureMockMvc
//@SpringBootTest
//public class EmailServiceControllerTest {
//
//	private static final String PATH = "/api/email";
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	@Test
//	public void whenPostThenIsOk() throws Exception {
//		
//		mockMvc.perform(post(PATH)
//							.content(new ObjectMapper().writeValueAsString(new Subscription()))
//							.contentType(MediaType.APPLICATION_JSON))
//			.andExpect(status().isOk());
//		
//	}
//	
//	@Test
//	public void whenPostThenIsBadRequest() throws Exception {
//		
//		mockMvc.perform(post(PATH)
//							.contentType(MediaType.APPLICATION_JSON)
//							.content(""))
//			.andExpect(status().isBadRequest());
//	}
//}
