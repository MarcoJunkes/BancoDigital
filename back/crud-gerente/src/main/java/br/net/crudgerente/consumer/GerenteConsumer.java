package br.net.crudgerente.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.crudgerente.model.Gerente;
import br.net.crudgerente.model.InsercaoGerenteEvent;
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

            String cpf = gerente.getCPF();
            String nome = gerente.getNome();

            InsercaoGerenteEvent insercaoGerenteEvent = new InsercaoGerenteEvent();
            insercaoGerenteEvent.setCpf(cpf);
            insercaoGerenteEvent.setNome(nome);

            String json = objectMapper.writeValueAsString(insercaoGerenteEvent);
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", e.getMessage());
        }
    }
}
