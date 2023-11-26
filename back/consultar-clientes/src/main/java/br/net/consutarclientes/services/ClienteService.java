package br.net.consutarclientes.services;

import br.net.consutarclientes.model.Cliente;
import br.net.consutarclientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente inserirCliente(Cliente cliente) {
        return repo.save(cliente);
    }

    public void alterarCliente(Cliente cliente) {
        Cliente c = repo.findByCPF(cliente.getCPF());

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

        repo.save(c);
    }
}
