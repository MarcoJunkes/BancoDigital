package br.net.consutarclientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.net.consutarclientes.repository.ClienteRepository;
import br.net.consutarclientes.model.AuthCliente;
import br.net.consutarclientes.model.Cliente;

@CrossOrigin
@RestController
public class ClienteREST {
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/clientes")
    public List<Cliente> obterCliente(){
        List<Cliente> lista = repo.findAll();
        return lista.stream().map(e -> mapper.map(e, Cliente.class)).collect(Collectors.toList());
    }

    @GetMapping("/clientes/{cpf}")
    public Cliente obterCliente(@PathVariable("cpf") String cpf){
        return repo.findByCPF(cpf);
    }

    @RabbitListener(queues = "service_cliente__request_buscarcpf")
    public void obterClientePorCPF(String cpf) throws JsonMappingException, JsonProcessingException{
        Cliente c = repo.findByCPF(cpf);
        AuthCliente cliente = new AuthCliente();
        cliente.setId(c.getId());
        cliente.setEmail(c.getEmail());
        cliente.setCPF(c.getCPF());

        String json = objectMapper.writeValueAsString(cliente);
        rabbitTemplate.convertAndSend("service_cliente__response_buscarcpf", json);
    }

    @RabbitListener(queues = "service_cliente__request_buscarcpf2")
    public void obterClientePorCPF2(String cpf) throws JsonMappingException, JsonProcessingException{
        Cliente c = repo.findByCPF(cpf);
        AuthCliente cliente = new AuthCliente();
        cliente.setId(c.getId());
        cliente.setEmail(c.getEmail());
        cliente.setCPF(c.getCPF());

        String json = objectMapper.writeValueAsString(cliente);
        rabbitTemplate.convertAndSend("service_cliente__response_buscarcpf2", json);
    }

    @PostMapping("/clientes")
    public Cliente inserirCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = repo.save(mapper.map(cliente, Cliente.class));
        return clienteSalvo;
    }

    @PutMapping("/clientes/{id}")
    public Cliente alterarCliente(@PathVariable("id") int id, @RequestBody Cliente cliente){
        Cliente c = repo.findById((long) id).orElse(null);

        if (c != null) {
            c.setNome(cliente.getNome());
            c.setEmail(cliente.getEmail());
            c.setCPF(cliente.getCPF());
            c.setTelefone(cliente.getTelefone());
            c.setSalario(cliente.getSalario());
            c.setRua(cliente.getRua());
            c.setBairro(cliente.getBairro());
            c.setNumero(cliente.getNumero());
            c.setComplemento(cliente.getComplemento());
            c.setCEP(cliente.getCEP());
            c.setCidade(cliente.getCidade());
            c.setEstado(cliente.getEstado());
            Cliente clienteAtualizado = repo.save(c);
            return mapper.map(clienteAtualizado, Cliente.class);
        }
        return null;
    }

    @DeleteMapping("/clientes/{id}")
    public Cliente removerCliente(@PathVariable("id") int id){
        Cliente cliente = repo.findById((long) id).orElse(null);

        if(cliente != null){
            repo.deleteById((long) id);
            return mapper.map(cliente, Cliente.class);
        }

        return cliente;
    }
}
