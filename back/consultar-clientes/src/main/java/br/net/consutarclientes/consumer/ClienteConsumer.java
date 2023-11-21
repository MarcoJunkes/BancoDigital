package br.net.consutarclientes.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.consutarclientes.model.Cliente;
import br.net.consutarclientes.model.NovaContaEvent;
import br.net.consutarclientes.rest.ClienteREST;

@Component
public class ClienteConsumer {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClienteREST clienteREST;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "service_cliente__request_autocadastro")
    public void autoCadastro(String msg) throws JsonMappingException, JsonProcessingException{
        try{
            var cliente = objectMapper.readValue(msg, Cliente.class);
            clienteREST.inserirCliente(cliente);

            String cpf = cliente.getCPF();
            String nome = cliente.getNome();
            float salario = cliente.getSalario();

            NovaContaEvent novaConta = new NovaContaEvent();
            novaConta.setCpf(cpf);
            novaConta.setNome(nome);
            novaConta.setSalario(salario);

            String json = objectMapper.writeValueAsString(novaConta);
            rabbitTemplate.convertAndSend("service_cliente__response_autocadastro", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_cliente__response_autocadastro", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            rabbitTemplate.convertAndSend("service_cliente__response_autocadastro", e.getMessage());
        }
    }
}
