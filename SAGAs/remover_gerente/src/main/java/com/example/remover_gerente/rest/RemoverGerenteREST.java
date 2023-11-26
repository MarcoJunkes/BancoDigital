package com.example.remover_gerente.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.remover_gerente.model.RemocaoGerenteEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class RemoverGerenteREST {
    private static final Logger LOGGER = LoggerFactory.getLogger(RemoverGerenteREST.class);
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    
    @DeleteMapping("/gerentes/{id}")
    public ResponseEntity<?> removerGerentes(@PathVariable int id) throws JsonProcessingException {
        RemocaoGerenteEvent remocaoGerenteEvent = new RemocaoGerenteEvent();
        remocaoGerenteEvent.setId(id);
        String json = objectMapper.writeValueAsString(remocaoGerenteEvent);
        rabbitTemplate.convertAndSend("service_gerente__request_remover_gerente", json);
        LOGGER.info("GERENTE REMOVAL -- Sent to queue: service_gerente__request_remover_gerente |");
        return ResponseEntity.ok().build();
    }

    @RabbitListener(queues = "service_gerente__response_remover_gerente")
    public void receberResposta(String msg) throws JsonProcessingException{
        RemocaoGerenteEvent gerente = objectMapper.readValue(msg, RemocaoGerenteEvent.class);
        String json = objectMapper.writeValueAsString(gerente);
        rabbitTemplate.convertAndSend("contas_service__gerente_excluido", json);
        LOGGER.info("GERENTE REMOVAL -- Sent to queue: contas_service__gerente_excluido |");
    }

}
