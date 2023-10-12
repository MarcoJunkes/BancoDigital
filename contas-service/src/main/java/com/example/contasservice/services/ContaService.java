package com.example.contasservice.services;

import com.example.contasservice.dtos.NovaContaRequestDTO;
import com.example.contasservice.models.Cliente;
import com.example.contasservice.models.Conta;
import com.example.contasservice.repositories.ClienteRepository;
import com.example.contasservice.repositories.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public Conta criarConta(NovaContaRequestDTO novaContaRequest) {
        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(novaContaRequest.getCpf());
        novoCliente.setNome(novaContaRequest.getNome());
        Cliente cliente = clienteRepository.save(novoCliente);

        Conta conta = new Conta();
        conta.setCliente(cliente);
        if (novaContaRequest.getSalario() >= 2000) {
            conta.setLimite(novaContaRequest.getSalario() / 2);
        }
        conta.setGerente(null);
        conta.setSaldo(0f);
        conta.setStatus("pendente");
        return contaRepository.save(conta);
    }
}
