package com.example.remover_gerente.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {

    @Value("service_gerente__request_remover_gerente")
    private String requestRemoverGerente;

    @Value("service_gerente__response_remover_gerente")
    private String responseInserirGerente;

    @Bean
    public Queue requestRemoverGerentQueue(){
        return new Queue(requestRemoverGerente, true);
    }

    @Bean
    public Queue responseRemoverGerenteQueue(){
        return new Queue(responseInserirGerente, true);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        converter.setCreateMessageIds(true);
        return converter;
    }
}
