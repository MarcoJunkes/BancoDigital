package com.example.inserir_gerente.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
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
    @Autowired
    
    @PostMapping("/inserirgerente")
    public ResponseEntity<?> enfileirarMensagem(@RequestBody Gerente gerente) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(gerente);
        rabbitTemplate.convertAndSend("service_gerente__request_inserir_gerente", json);
        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @RabbitListener(queues = "service_gerente__response_inserir_gerente")
    public void receberResposta(String msg){
        if("Sucesso".equals(msg)){
            System.out.println("OK");           
        } else {
            System.out.println("ERRO");
        }
    }

}
