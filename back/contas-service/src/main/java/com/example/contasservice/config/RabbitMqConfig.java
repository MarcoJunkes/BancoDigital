package com.example.contasservice.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer {

    @Value("service_conta__request_aprovar_conta")
    private String requestAprovarConta;

    @Value("service_conta__response_aprovar_conta")
    private String responseAprovarConta;

    @Value("service_conta__request_rejeitar_conta")
    private String rejeitarConta;

    @Value("service_conta__response_rejeitar_conta")
    private String rejeitarContaResponse;

    @Bean
    public Queue requestAprovarContaQueue(){
        return new Queue(requestAprovarConta, true);
    }

    @Bean
    public Queue responseAprovarContaQueue(){
        return new Queue(responseAprovarConta, true);
    }

    @Bean
    public Queue rejeitarContaQueue(){
        return new Queue(rejeitarConta, true);
    }

    @Bean
    public Queue rejeitarContaResponseQueue(){
        return new Queue(rejeitarContaResponse, true);
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

    @Bean
    public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
        factory.setMessageConverter(consumerJackson2MessageConverter());
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }

    @Override
    public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
        registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

}
