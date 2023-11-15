package br.net.crudgerente.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.crudgerente.model.Gerente;
import br.net.crudgerente.rest.GerenteREST;

@Component
public class GerenteConsumer {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private GerenteREST gerenteREST;

    @RabbitListener(queues = "service_gerente__request_inserir_gerente")
    public void receiveMessage(String msg) throws JsonMappingException, JsonProcessingException{
        var gerente = objectMapper.readValue(msg, Gerente.class);
        gerenteREST.inserirGerente(gerente);
    }
}
