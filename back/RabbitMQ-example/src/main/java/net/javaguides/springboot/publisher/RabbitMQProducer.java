package net.javaguides.springboot.publisher;

import net.javaguides.springboot.controller.AlterarPerfilEvent;
import net.javaguides.springboot.controller.ExcluirGerenteEvent;
import net.javaguides.springboot.controller.NovaContaEvent;
import net.javaguides.springboot.controller.NovoGerenteEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void autocadastro(NovaContaEvent novaContaEvent){
        LOGGER.info(String.format("Message sent (cadastro) -> %s", novaContaEvent.getCpf()));
        rabbitTemplate.convertAndSend("contas_service__novo_cliente", novaContaEvent);
    }

    public void alterarPerfil(AlterarPerfilEvent alterarPerfilEvent){
        LOGGER.info(String.format("Message sent (alterar perfil) -> %s", alterarPerfilEvent.getNome()));
        rabbitTemplate.convertAndSend("contas_service__alterar_perfil", alterarPerfilEvent);
    }

    public void excluirGerente(ExcluirGerenteEvent excluirGerenteEvent) {
        LOGGER.info(String.format("Message sent (excluir gerente) -> %s", excluirGerenteEvent.getCpf()));
        rabbitTemplate.convertAndSend("contas_service__gerente_excluido", excluirGerenteEvent);
    }

    public void inserirGerente(NovoGerenteEvent novoGerenteEvent) {
        LOGGER.info(String.format("Message sent (novo gerente) -> %s", novoGerenteEvent.getNome()));
        rabbitTemplate.convertAndSend("contas_service__novo_gerente", novoGerenteEvent);
    }
}
