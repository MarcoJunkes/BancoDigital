package com.example.authservice.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.authservice.UsuarioController;
import com.example.authservice.dtos.CadastroRequestDTO;
import com.example.authservice.dtos.EmailMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UsuarioController usuarioController;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "service_auth__criar_registro_cliente")
    public void criarRegistroCliente(String msg) throws JsonMappingException, JsonProcessingException{
        var usuario = objectMapper.readValue(msg, CadastroRequestDTO.class);
        usuarioController.cadastro(usuario);

        String email = objectMapper.writeValueAsString(usuario.getEmail());
        String senha = objectMapper.writeValueAsString(usuario.getSenha());

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setTo(email);
        emailMessage.setSubject("Aprovação Conta - Sua senha");
        emailMessage.setMessage("Parabéns, sua conta foi aprovada. Aqui está sua senha de acesso: " + senha);

        String json = objectMapper.writeValueAsString(emailMessage);
        rabbitTemplate.convertAndSend("service_auth__enviar_dados_email", json);
    }
}
