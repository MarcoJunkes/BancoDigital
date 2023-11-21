package com.example.inserir_gerente.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.inserir_gerente.model.Gerente;
import com.example.inserir_gerente.model.InsercaoGerenteEvent;
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
    public ResponseEntity inserirGerentes(@RequestBody Gerente gerenteRequest) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(gerenteRequest);
        rabbitTemplate.convertAndSend("service_gerente__request_inserir_gerente", json);
        return ResponseEntity.created(null).build();
    }

    @RabbitListener(queues = "service_gerente__response_inserir_gerente")
    public void inserirGerenteConta(String msg) {
        try {
            System.out.println("Mensagem: " + msg);
            InsercaoGerenteEvent gerente = objectMapper.readValue(msg, InsercaoGerenteEvent.class);
            System.out.println("Mensagem convertida: " + gerente);
            String json = objectMapper.writeValueAsString(gerente);
            System.out.println("Mensagem convertida novamente: " + json);
            rabbitTemplate.convertAndSend("contas_service__novo_gerente", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente");
        }
    }
    
}
