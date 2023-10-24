package com.example.contasservice.services;

import com.example.contasservice.dtos.ListagemClienteDTO;
import com.example.contasservice.models.Cliente;
import com.example.contasservice.models.Conta;
import com.example.contasservice.repositories.ClienteRepository;
import com.example.contasservice.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ClienteService {
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ListagemClienteDTO> listarClientes() {
        List<Conta> contas = contaRepository.findAll();
        List<ListagemClienteDTO> clientes = new ArrayList<>();

        for (Conta conta: contas) {
            ListagemClienteDTO cliente = new ListagemClienteDTO();
            cliente.setCpf(conta.getCliente().getCpf());
            cliente.setSaldo(conta.getSaldo());
            clientes.add(cliente);
        }

        return clientes;
    }

    public Cliente getByCpf(String cpf) {
        return clienteRepository.getByCpf(cpf);
    }

    public void aprovarCliente(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.ATIVA);
        contaRepository.save(conta);
    }

    public void rejeitarCliente(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.REJEITADA);
        contaRepository.save(conta);
    }
}
