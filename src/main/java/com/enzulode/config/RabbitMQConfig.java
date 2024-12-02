package com.enzulode.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Bean
  public FanoutExchange fanoutExchange(@Value("${interactive.exchange}") String exchange) {
    return new FanoutExchange(exchange);
  }

  @Bean
  public Queue updatesQueue() {
    return new AnonymousQueue();
  }

  @Bean
  public Binding updatesQueueBindingFanout(FanoutExchange exchange, Queue updatesQueue) {
    return BindingBuilder.bind(updatesQueue).to(exchange);
  }

  @Bean
  public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
    return new RabbitAdmin(connectionFactory);
  }

  @Bean
  public Jackson2JsonMessageConverter defaultMessageConverter(ObjectMapper mapper) {
    return new Jackson2JsonMessageConverter(mapper);
  }
}
