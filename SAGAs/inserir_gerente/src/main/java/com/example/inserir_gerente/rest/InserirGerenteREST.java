package com.example.inserir_gerente.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.inserir_gerente.model.Cadastro;
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

    @PostMapping("/gerentes/inserir")
    public ResponseEntity inserirGerentes(@RequestBody Gerente gerenteRequest) throws JsonProcessingException{

        var json = objectMapper.writeValueAsString(gerenteRequest);
        rabbitTemplate.convertAndSend("service_gerente__request_inserir_gerente", json);
        return ResponseEntity.created(null).build();
    }

    @RabbitListener(queues = "service_gerente__response_inserir_gerente")
    public void inserirGerenteConta(String msg) {
        try {
            InsercaoGerenteEvent gerente = objectMapper.readValue(msg, InsercaoGerenteEvent.class);
            String json = objectMapper.writeValueAsString(gerente);
            rabbitTemplate.convertAndSend("contas_service__novo_gerente", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente");
        }
    }

    @RabbitListener(queues = "service_gerente__response_inserir_gerente__dados_cadastro")
    public void receberDadosCadastro(String msg){
        try{
            System.out.println("Dados não convertido: " + msg);
            Cadastro cadastro = objectMapper.readValue(msg, Cadastro.class);
            System.out.println("Dados de cadastro" + cadastro);

        } catch (JsonProcessingException e){
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente dos dados de cadastro");
        }
    }
    
}
