package com.example.inserir_gerente.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.inserir_gerente.model.Gerente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class InserirGerenteREST {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/inserirGerentes")
    public ResponseEntity inserirGerentes(@RequestBody Gerente gerenteRequest) throws JsonProcessingException{
        var json = objectMapper.writeValueAsString(gerenteRequest);
        rabbitTemplate.convertAndSend("service_gerente__request_inserir_gerente", json);
        return ResponseEntity.created(null).build();
    }

}
