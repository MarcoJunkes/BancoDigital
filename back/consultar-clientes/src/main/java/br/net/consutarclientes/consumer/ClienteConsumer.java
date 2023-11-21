package br.net.consutarclientes.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.consutarclientes.model.Cliente;
import br.net.consutarclientes.rest.ClienteREST;

@Component
public class ClienteConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteREST clienteREST;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "service_cliente__request_autocadastro")
    public void autoCadastro(String msg) throws JsonMappingException, JsonProcessingException{
        var cliente = objectMapper.readValue(msg, Cliente.class);
        clienteREST.inserirCliente(cliente);
    }
}
