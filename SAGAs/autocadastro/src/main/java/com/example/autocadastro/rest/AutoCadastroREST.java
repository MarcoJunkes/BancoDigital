package com.example.autocadastro.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.autocadastro.model.Cliente;
import com.example.autocadastro.model.NovaContaEvent;
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

    @RabbitListener(queues = "service_cliente__response_autocadastro")
    public void criarConta(String msg){
        try{
            System.out.println("Mensagem: " + msg);
            NovaContaEvent novaConta = objectMapper.readValue(msg, NovaContaEvent.class);
            String json = objectMapper.writeValueAsString(novaConta);
            System.out.println("Mensagem convertida: " + msg);
            rabbitTemplate.convertAndSend("contas_service__novo_cliente", json);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente dos dados de cadastro");
        }
    }
}
