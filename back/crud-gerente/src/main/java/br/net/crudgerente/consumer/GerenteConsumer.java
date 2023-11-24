package br.net.crudgerente.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.crudgerente.model.Gerente;
import br.net.crudgerente.rest.GerenteREST;

@Component
public class GerenteConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GerenteREST gerenteREST;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "service_gerente__request_inserir_gerente")
    public void inserirGerente(String msg) throws JsonMappingException, JsonProcessingException{
        try{
            var gerente = objectMapper.readValue(msg, Gerente.class);
            gerenteREST.inserirGerente(gerente);

            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", "Sucesso");
        } catch(Exception e){
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", e);
        }
    }

    @RabbitListener(queues = "service_gerente__request_remover_gerente")
    public void removerGerente(int id) throws JsonMappingException, JsonProcessingException{
        try{
            gerenteREST.removerGerente(id);

            rabbitTemplate.convertAndSend("service_gerente__response_remover_gerente", "Sucesso");
        } catch(Exception e){
            rabbitTemplate.convertAndSend("service_gerente__response_remover_gerente", e);
        }
    }
}
