package com.example.remover_gerente.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class RemoverGerenteREST {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    
    @PostMapping("/removerGerente")
    public ResponseEntity<?> enfileirarMensagem(@RequestBody Number id) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(id);
        rabbitTemplate.convertAndSend("service_gerente__request_remover_gerente", json);
        return new ResponseEntity<>("Enfileirado: " + json, HttpStatus.OK);
    }
    @RabbitListener(queues = "service_gerente__response_remover_gerente")
    public void receberResposta(String msg){
        if("Sucesso".equals(msg)){
            System.out.println("OK");           
        } else {
            System.out.println("ERRO");
        }
    }

}
