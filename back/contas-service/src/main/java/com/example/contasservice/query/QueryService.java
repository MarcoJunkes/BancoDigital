package com.example.contasservice.query;

import com.example.contasservice.model.Conta;
import com.example.contasservice.repository.read.ContaReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    private ContaReadRepository contaReadRepository;

    @Autowired
    QueryService(ContaReadRepository contaReadRepository) {
        this.contaReadRepository = contaReadRepository;
    }

    public Conta consultarPorCpf(String cpf) {
        return new Conta();
    }

    public void consultaExtrato(Long numero) {}

    public void consultaClientes(String cpf, String nome) {}

    public void consultarTop3() {}

    public void consultarContasPorGerente(String cpf) {}
}
