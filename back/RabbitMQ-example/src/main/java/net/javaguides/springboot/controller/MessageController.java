package net.javaguides.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springboot.publisher.RabbitMQProducer;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    
    private RabbitMQProducer producer;

    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    // http://localhost:8080/api/v1/publish?message=hello

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message){
        NovaContaEvent novaContaEvent = new NovaContaEvent();
        novaContaEvent.setCpf("11");
        novaContaEvent.setNome("nome");
        novaContaEvent.setSalario(2000f);

        producer.sendMessage(novaContaEvent);
        return ResponseEntity.ok("Message sent to RabbitMQ ...");
    }
}
