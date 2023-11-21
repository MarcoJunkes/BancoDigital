package com.example.autocadastro.rest;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.autocadastro.model.Cliente;
import com.example.autocadastro.model.NovaContaEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
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
            NovaContaEvent novaConta = objectMapper.readValue(msg, NovaContaEvent.class);
            String json = objectMapper.writeValueAsString(novaConta);
            rabbitTemplate.convertAndSend("contas_service__novo_cliente", json);
        } catch (JsonProcessingException e){
            e.printStackTrace();
            System.out.println("Erro ao processar a resposta do serviço de gerente dos dados de cadastro");
        }
    }

    @PostMapping("/aprovarConta/{cpf}")
    public ResponseEntity aprovarConta(@PathVariable String cpf) throws JsonProcessingException{
        String msg = objectMapper.writeValueAsString(cpf);
        rabbitTemplate.convertAndSend("service_conta__request_aprovar_conta", msg);
        return ResponseEntity.created(null).build();
    }

    @RabbitListener(queues = "service_conta__response_aprovar_conta")
    public void criarRegistroConta(String msg) throws JsonMappingException, JsonProcessingException{
        String cpf = objectMapper.readValue(msg, String.class);
        System.out.println("CPF aprovado2: " + cpf);
    }
}
