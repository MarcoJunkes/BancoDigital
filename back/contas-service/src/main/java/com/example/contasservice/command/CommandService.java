package com.example.contasservice.command;

import com.example.contasservice.event.NovaContaEvent;
import com.example.contasservice.model.Conta;
import com.example.contasservice.repository.write.ContaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {
    @Autowired
    private ContaRepository contaRepository;

//    @RabbitListener(queues="contas_service__novo_cliente")
    public void createConta(NovaContaEvent novaContaEvent) {
        // save on write
        Conta conta = new Conta();



        Conta contaSaved = contaRepository.save(conta);

        // send to rabbitmq
//        NovaContaEvent event = new NovaContaEvent();

    }
}
