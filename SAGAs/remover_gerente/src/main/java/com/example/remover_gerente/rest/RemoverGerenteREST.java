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

import com.example.remover_gerente.model.RemocaoGerenteEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin
@RestController
public class RemoverGerenteREST {
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private ObjectMapper objectMapper;
    
    @PostMapping("/gerentes/remover")
    public ResponseEntity<?> removerGerentes(@RequestBody String msg) throws JsonProcessingException {
        rabbitTemplate.convertAndSend("service_gerente__request_remover_gerente", msg);
        return ResponseEntity.created(null).build();
    }

    @RabbitListener(queues = "service_gerente__response_remover_gerente")
    public void receberResposta(String msg){
        try {
            RemocaoGerenteEvent gerente = objectMapper.readValue(msg, RemocaoGerenteEvent.class);
            String json = objectMapper.writeValueAsString(gerente);
            rabbitTemplate.convertAndSend("ccontas_service__gerente_excluido", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente");
        }
    }

}
