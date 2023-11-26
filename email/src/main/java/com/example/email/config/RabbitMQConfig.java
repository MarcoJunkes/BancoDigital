package com.example.email.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {
    @Value("service_auth__enviar_dados_email")
    private String enviarDadosEmail;

    @Value("service_conta__enviar_dados_recusados")
    private String enviarDadosRecusados;

    @Bean
    public Queue enviarDadosEmailQueue(){
        return new Queue(enviarDadosEmail, true);
    }


    @Bean
    public Queue enviarDadosRecusadosQueue(){
        return new Queue(enviarDadosRecusados, true);
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
