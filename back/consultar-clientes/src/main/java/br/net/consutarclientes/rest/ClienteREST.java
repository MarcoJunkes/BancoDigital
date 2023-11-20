package br.net.consutarclientes.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.net.consutarclientes.repository.ClienteRepository;

import br.net.consutarclientes.model.Cliente;

@CrossOrigin
@RestController
public class ClienteREST {
    @Autowired
    private ClienteRepository repo;
    @Autowired
    private ModelMapper mapper;

    @GetMapping("/clientes")
    public List<Cliente> obterCliente(){
        List<Cliente> lista = repo.findAll();
        return lista.stream().map(e -> mapper.map(e, Cliente.class)).collect(Collectors.toList());
    }

    @GetMapping("/clientes/{id}")
    public Cliente obterCliente(@PathVariable("id") int id){
        return repo.findById((long) id).orElse(null);
    }

    @PostMapping("/clientes")
    public Cliente inserirCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = repo.save(mapper.map(cliente, Cliente.class));
        return clienteSalvo;
    }

    @PutMapping("/clientes/{id}")
    public Cliente alterarCliente(@PathVariable("id") int id, @RequestBody Cliente cliente){
        Cliente c = repo.findById((long) id).orElse(null);

        if(c != null){
            c.setNome(cliente.getNome());
            c.setCPF(cliente.getCPF());
            c.setCidade(cliente.getCidade());
            c.setEstado(cliente.getEstado());
            c.setSaldo(cliente.getSaldo());
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
