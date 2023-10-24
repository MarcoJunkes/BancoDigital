package com.example.contasservice.services;

import com.example.contasservice.dtos.*;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.GerenteNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.models.Cliente;
import com.example.contasservice.models.Conta;
import com.example.contasservice.models.Gerente;
import com.example.contasservice.repositories.ClienteRepository;
import com.example.contasservice.repositories.ContaRepository;
import com.example.contasservice.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private GerenteRepository gerenteRepository;

    public Conta criarConta(NovaContaRequestDTO novaContaRequest) throws GerenteNotFound {
        List<Object> gerenteRaw = gerenteRepository.getGerenteWithLessClients();
        if (gerenteRaw.size() == 0) {
            throw new GerenteNotFound();
        }
        String gerenteCpf = (String) ((Object[]) gerenteRaw.get(0))[1];
        Gerente gerente = gerenteRepository.findById(gerenteCpf).get();

        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(novaContaRequest.getCpf());
        novoCliente.setNome(novaContaRequest.getNome());
        Cliente cliente = clienteRepository.save(novoCliente);

        Conta conta = new Conta();
        conta.setCliente(cliente);
        if (novaContaRequest.getSalario() >= 2000) {
            conta.setLimite(novaContaRequest.getSalario() / 2);
        }

        conta.setGerente(gerente);
        conta.setSaldo(0f);
        conta.setStatus(Conta.StatusConta.PENDENTE_APROVACAO);
        return contaRepository.save(conta);
    }

    public Conta getByCliente(String cpf) {
        return contaRepository.getByClienteCpf(cpf);
    }

    public ListagemContaResponseDTO getByGerente(String cpf) {
        ListagemContaResponseDTO listagemContaResponseDTO = new ListagemContaResponseDTO();
        listagemContaResponseDTO.setNumeroClientes(contaRepository.countByGerenteCpf(cpf));
        listagemContaResponseDTO.setSaldoPositivo(contaRepository.sumPositiveSaldoByGerenteCpf(cpf));
        listagemContaResponseDTO.setSaldoNegativo(contaRepository.sumNegativeSaldoByGerenteCpf(cpf));
        return listagemContaResponseDTO;
    }

    public List<ListagemClienteDTO> getTop3() {
        List<ListagemClienteDTO> contas = new ArrayList<>();
        List rawTop3Contas = contaRepository.getTop3();
        for (Object contaRaw: rawTop3Contas) {
            String cpf = (String) ((Object[]) contaRaw)[0];
            Float saldo = (Float) ((Object[]) contaRaw)[1];
            ListagemClienteDTO cliente = new ListagemClienteDTO(cpf, saldo);
            contas.add(cliente);
        }

        return contas;
    }

    public void sacar(Long numero, SaqueDTO saqueDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }

        if ((saqueDTO.getValor() + conta.getLimite()) < 0) {
            throw new ValorNegativoBadRequest("valor");
        }
        Float novoSaldo = conta.getSaldo() + conta.getLimite() - saqueDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }

        conta.setSaldo(conta.getSaldo() - saqueDTO.getValor());
        contaRepository.save(conta);
    }

    public void depositar(Long numero, DepositoDTO depositoDTO) throws ContaNotFound {
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }
        conta.setSaldo(conta.getSaldo() + depositoDTO.getValor());
        contaRepository.save(conta);
    }

    public void transferir(Long contaOrigemId, TransferenciaDTO transferenciaDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta contaOrigem = contaRepository.findById(contaOrigemId).get();
        Conta contaDestino = contaRepository.findById(transferenciaDTO.getContaDestino()).get();
        if (contaOrigem == null || contaDestino == null) {
            throw new ContaNotFound();
        }

        Float novoSaldo = contaOrigem.getSaldo() + contaOrigem.getLimite() - transferenciaDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferenciaDTO.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transferenciaDTO.getValor());
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);
    }
}
