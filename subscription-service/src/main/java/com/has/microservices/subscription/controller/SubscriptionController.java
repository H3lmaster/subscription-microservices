package com.has.microservices.subscription.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.has.microservices.subscription.builder.SubscriptionBuilder;
import com.has.microservices.subscription.dto.SubscriptionDTO;
import com.has.microservices.subscription.model.Subscription;
import com.has.microservices.subscription.repository.SubscriptionRepository;

@RestController
@RequestMapping(value = "/api/subscription")
public class SubscriptionController {
	
	@Value("${email.routing-key}")
	private String emailRoutingKey;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Resource
	private SubscriptionRepository subscriptionRepository;
	
	@GetMapping
	public List<Subscription> getAll() {
		return (List<Subscription>) subscriptionRepository.findAll();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Subscription> get(@PathVariable String id) {
	
		Optional<Subscription> subscription = subscriptionRepository.findById(id);
		
		if ( !subscription.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(subscription.get());
	}
	
	@PostMapping
	public ResponseEntity save(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
		
		// Send subscription request to subscription queue to be processed later
		rabbitTemplate.convertAndSend(subscriptionDTO);
		
		return ResponseEntity.ok(subscriptionDTO);
		
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Subscription> create(@Valid @RequestBody SubscriptionDTO subscriptionDTO) {
		
		Subscription subscription = new SubscriptionBuilder().buildFromDTO(subscriptionDTO);
		
		subscription = subscriptionRepository.save(subscription);
		
		// Send email request to email queue 
		rabbitTemplate.convertAndSend(emailRoutingKey, subscription);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(subscription);
		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Subscription> delete(@PathVariable String id) {
		
		Optional<Subscription> subscription = subscriptionRepository.findById(id);
		
		if ( !subscription.isPresent() ) {
			return ResponseEntity.notFound().build();
		}
		
		subscriptionRepository.delete(subscription.get());
				
		return ResponseEntity.ok(subscription.get());
	}

}
