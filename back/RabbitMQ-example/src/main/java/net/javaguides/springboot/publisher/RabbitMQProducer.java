package net.javaguides.springboot.publisher;

import net.javaguides.springboot.controller.NovaContaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
     
    // setting variables 

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(NovaContaEvent novaContaEvent){
        LOGGER.info(String.format("Message sent -> %s", novaContaEvent.getCpf()));
        rabbitTemplate.convertAndSend("contas_service__novo_cliente", novaContaEvent);
    }
}
