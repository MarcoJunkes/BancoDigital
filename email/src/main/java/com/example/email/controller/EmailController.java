package com.example.email.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.resource.EmailMessage;
import com.example.email.service.EmailSenderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class EmailController {
    @Autowired
    private ObjectMapper objectMapper;

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService){
        this.emailSenderService = emailSenderService;
    }

    @RabbitListener(queues = "service_auth__enviar_dados_email")
    public void sendEmail(String msg) throws JsonMappingException, JsonProcessingException{
        var emailMessage = objectMapper.readValue(msg, EmailMessage.class);
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
    }

    @RabbitListener(queues = "service_conta__enviar_dados_recusados")
    public void sendEmailDadosRecusados(String msg) throws JsonMappingException, JsonProcessingException{
        var emailMessage = objectMapper.readValue(msg, EmailMessage.class);
        this.emailSenderService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getMessage());
    }
}
