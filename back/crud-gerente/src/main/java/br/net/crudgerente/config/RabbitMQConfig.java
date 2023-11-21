package br.net.crudgerente.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class RabbitMQConfig {
    @Value("service_gerente__request_inserir_gerente")
    private String requestInserirGerente;

    @Value("service_gerente__response_inserir_gerente")
    private String responseInserirGerente;

    @Value("service_gerente__response_inserir_gerente__dados_cadastro")
    private String responseInserirGerenteDadosCadastro;

    @Bean
    public Queue requestInserirGerentQueue(){
        return new Queue(requestInserirGerente, true);
    }

    @Bean
    public Queue responseInserirGerenteQueue(){
        return new Queue(responseInserirGerente, true);
    }

    @Bean
    public Queue responseInserirGerenteDadosCadastroQueue(){
        return new Queue(responseInserirGerenteDadosCadastro, true);
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
