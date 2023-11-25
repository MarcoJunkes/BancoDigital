package br.net.consutarclientes.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {
    @Value("service_cliente__request_autocadastro")
    private String requestAutoCadastro;

    @Value("service_cliente__response_autocadastro")
    private String responseAutoCadastro;

    @Value("service_cliente__request_buscarcpf")
    private String requestBuscarCPF;

    @Value("service_cliente__response_buscarcpf")
    private String responseBuscarCPF;

    @Value("service_cliente__request_buscarcpf2")
    private String rejeitarContaRequest2;

    @Value("service_cliente__response_buscarcpf2")
    private String rejeitarContaResponse2;

    @Bean
    public Queue requestBuscarCPFQueue(){
        return new Queue(requestBuscarCPF, true);
    }

    @Bean
    public Queue responseBuscarCPFQueue(){
        return new Queue(responseBuscarCPF, true);
    }

    @Bean
    public Queue requestAutoCadastroQueue(){
        return new Queue(requestAutoCadastro, true);
    }

    @Bean Queue responseAutoCadastroQueue(){
        return new Queue(responseAutoCadastro, true);
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
