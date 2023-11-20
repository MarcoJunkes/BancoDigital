package com.example.inserir_gerente.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig{

    @Value("service_gerente__request_inserir_gerente")
    private String requestInserirGerente;

    @Value("service_gerente__response_inserir_gerente")
    private String responseInserirGerente;

    @Value("contas_service__novo_gerente")
    private String contaServiceNovoGerente;

    @Value("contas_service__novo_gerente__response")
    private String contaServiceNovoGerenteResponse;

    @Bean
    public Queue requestInserirGerentQueue(){
        return new Queue(requestInserirGerente, true);
    }

    @Bean
    public Queue responseInserirGerenteQueue(){
        return new Queue(responseInserirGerente, true);
    }

    @Bean
    public Queue contaServiceInserirGerenteQueue(){
        return new Queue(contaServiceNovoGerente, true);
    }

    @Bean
    public Queue contaServiceInserirGerenteResponseQueue(){
        return new Queue(contaServiceNovoGerenteResponse, true);
    }

    @Bean
    public ObjectMapper objectMapper(){
        return JsonMapper.builder().findAndAddModules().build();
    }
}
