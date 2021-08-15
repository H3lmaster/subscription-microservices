package com.has.microservices.subscription.processor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	@Value("${spring.rabbitmq.template.default-receive-queue}")
	private String queueName;

	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;

	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;

	@Bean
	public Queue subscriptionQueue() {
		return new org.springframework.amqp.core.Queue(queueName);
	}

	@Bean
	public DirectExchange emailExchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding subscriptionBinding(DirectExchange exchange,Queue queue){
		return BindingBuilder
				.bind(queue)
				.to(exchange)
				.with(routingKey);
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

}
