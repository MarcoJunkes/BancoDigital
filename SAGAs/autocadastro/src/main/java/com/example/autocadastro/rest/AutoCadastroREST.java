package com.example.autocadastro.rest;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.autocadastro.model.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class AutoCadastroREST {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/autocadastro")
    public ResponseEntity autoCadastro(@RequestBody Cliente clienteRequest) throws JsonProcessingException{
        var json = objectMapper.writeValueAsString(clienteRequest);
        rabbitTemplate.convertAndSend("service_cliente__request_autocadastro", json);
        return ResponseEntity.created(null).build();
    }
}
