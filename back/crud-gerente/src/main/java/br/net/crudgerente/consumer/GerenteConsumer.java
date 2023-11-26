package br.net.crudgerente.consumer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.crudgerente.model.Cadastro;
import br.net.crudgerente.model.Gerente;
import br.net.crudgerente.model.InsercaoGerenteEvent;
import br.net.crudgerente.model.RemocaoGerenteEvent;
import br.net.crudgerente.rest.GerenteREST;

@Component
public class GerenteConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GerenteConsumer.class);
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
            String email = gerente.getEmail();
            String senha = gerente.getSenha();

            InsercaoGerenteEvent insercaoGerenteEvent = new InsercaoGerenteEvent();
            insercaoGerenteEvent.setCpf(cpf);
            insercaoGerenteEvent.setNome(nome);

            Cadastro cadastro = new Cadastro();
            cadastro.setEmail(email);
            cadastro.setSenha(senha);
            cadastro.setPerfil("gerente");

            String json = objectMapper.writeValueAsString(insercaoGerenteEvent);
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", json);

            String json2 = objectMapper.writeValueAsString(cadastro);
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente__dados_cadastro",json2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_gerente__response_inserir_gerente", e.getMessage());
        }
    }

    @RabbitListener(queues = "service_gerente__request_remover_gerente")
    public void removerGerente(String msg ) throws JsonMappingException, JsonProcessingException{
        try{
            var gerente = objectMapper.readValue(msg, RemocaoGerenteEvent.class);
            List<Gerente> gerentes = gerenteREST.obterGerentes();
            String json = objectMapper.writeValueAsString(gerente);
            for(Gerente g: gerentes){
                if(g.getCPF().equals(gerente.getCpf())){
                    gerenteREST.removerGerente(g.getId());
                    rabbitTemplate.convertAndSend("service_gerente__response_remover_gerente", json);
                    LOGGER.info("GERENTE REMOVAL -- Sent to queue: service_gerente__response_remover_gerente"+json);
                    return;
                }
            }
             
            LOGGER.info("GERENTE REMOVAL -- Failed deletion, CPF not found");

            
        } catch(Exception e){
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_gerente__response_remover_gerente", e.getMessage());
        }
    }
}
