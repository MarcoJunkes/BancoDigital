package com.example.authservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.authservice.UsuarioController;
import com.example.authservice.dtos.CadastroRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UsuarioController usuarioController;

    @RabbitListener(queues = "service_gerente__response_inserir_gerente__dados_cadastro")
    public void criarRegistroCadastro(String msg) throws JsonMappingException, JsonProcessingException{
        var usuario = objectMapper.readValue(msg, CadastroRequestDTO.class);
        usuarioController.cadastro(usuario);
    }
}
