package net.javaguides.springboot.consumer;

import net.javaguides.springboot.controller.NovaContaEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

//    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
//    public void consume(NovaContaEvent message){
//        LOGGER.info(String.format("Message received -> %s", message));
//    }

//    @RabbitListener(queues = {"contas_service__novo_cliente__database_sync"})
//    public void consumeOther(NovaContaResponse message){
//        LOGGER.info(String.format("Message received -> %s", message));
//        LOGGER.info(String.format("Message received -> %s", message));
//    }
}
