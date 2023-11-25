package com.example.autocadastro.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig{

    @Value("service_cliente__request_autocadastro")
    private String requestAutoCadastro;

    @Value("service_cliente__response_autocadastro")
    private String responseAutoCadastro;

    @Value("contas_service__novo_cliente")
    private String novoCliente;

    @Value("service_conta__request_aprovar_conta")
    private String requestAprovarConta;

    @Value("service_conta__response_aprovar_conta")
    private String responseAprovarConta;

    @Value("service_cliente__request_buscarcpf")
    private String requestBuscarCPF;

    @Value("service_cliente__response_buscarcpf")
    private String responseBuscarCPF;

    @Value("service_auth__criar_registro_cliente")
    private String criarRegistro;

    @Value("service_conta__request_rejeitar_conta")
    private String rejeitarConta;

    @Value("service_conta__response_rejeitar_conta")
    private String rejeitarContaResponse;

    @Value("service_cliente__request_buscarcpf2")
    private String rejeitarContaRequest2;

    @Value("service_cliente__response_buscarcpf2")
    private String rejeitarContaResponse2;

    @Value("service_conta__enviar_dados_recusados")
    private String enviarDadosRecusados;

    @Bean
    public Queue requestAutoCadastroQueue(){
        return new Queue(requestAutoCadastro, true);
    }

    @Bean 
    public Queue responseAutoCadastroQueue(){
        return new Queue(responseAutoCadastro, true);
    }

    @Bean
    public Queue novoClientQueue(){
        return new Queue(novoCliente, true);
    }

    @Bean
    public Queue requestAprovarContaQueue(){
        return new Queue(requestAprovarConta, true);
    }

    @Bean
    public Queue responseAprovarContaQueue(){
        return new Queue(responseAprovarConta, true);
    }
    
    @Bean
    public Queue requestBuscarCPFQueue(){
        return new Queue(requestBuscarCPF, true);
    }

    @Bean
    public Queue responseBuscarCPFQueue(){
        return new Queue(responseBuscarCPF, true);
    }

    @Bean
    public Queue criarRegistroClientQueue(){
        return new Queue(criarRegistro, true);
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
    public Queue rejeitarContaRequest2Queue(){
        return new Queue(rejeitarContaRequest2, true);
    }

    @Bean
    public Queue rejeitarContaResponse2Queue(){
        return new Queue(rejeitarContaResponse2, true);
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