package com.example.contasservice.query;

import com.example.contasservice.dto.ClienteDTO;
import com.example.contasservice.dto.ContaResponseDTO;
import com.example.contasservice.dto.ContasGerenteDTO;
import com.example.contasservice.dto.ExtratoDTO;
import com.example.contasservice.model.Conta;
import com.example.contasservice.model.ContaRead;
import com.example.contasservice.model.MovimentacaoRead;
import com.example.contasservice.repository.read.ContaReadRepository;
import com.example.contasservice.repository.read.MovimentacaoReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class QueryService {
    private ContaReadRepository contaReadRepository;
    private MovimentacaoReadRepository movimentacaoReadRepository;
    @Autowired
    QueryService(ContaReadRepository contaReadRepository, MovimentacaoReadRepository movimentacaoReadRepository) {
        this.contaReadRepository = contaReadRepository;
        this.movimentacaoReadRepository = movimentacaoReadRepository;
    }

    public ContaResponseDTO consultarPorCpf(String cpf) {
        ContaRead contaRead = contaReadRepository.findContaReadByClienteCpf(cpf);
        ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
        contaResponseDTO.setNumero(contaRead.getNumero());
        contaResponseDTO.setLimite(contaRead.getLimite());
        contaResponseDTO.setSaldo(contaRead.getSaldo());

        return contaResponseDTO;
    }

    public List<ExtratoDTO> consultaExtrato(String cpf) {
        List<ExtratoDTO> extrato = new ArrayList<>();
        Set<MovimentacaoRead> movimentacoes = movimentacaoReadRepository.findByClienteCpf(cpf);

        for (MovimentacaoRead movimentacao : movimentacoes) {
            ExtratoDTO extratoDTO = new ExtratoDTO();
            extratoDTO.setId(movimentacao.getId());
            extratoDTO.setTipo(movimentacao.getTipo().toString());
            extratoDTO.setData(movimentacao.getData());
            extratoDTO.setDirecao(movimentacao.getDirecao().toString());
            extratoDTO.setValor(movimentacao.getValor());
            extratoDTO.setOrigem(movimentacao.getContaOrigem().getNumero().toString());
            if (movimentacao.getContaDestino() != null) {
                extratoDTO.setDestino(movimentacao.getContaDestino().getNumero().toString());
            }
            extrato.add(extratoDTO);
        }

        return extrato;
    }

    public List<ClienteDTO> consultaClientes(String cpf, String nome) {
        List<ClienteDTO> clientes = new ArrayList<>();
        List<ContaRead> contas;
        if (cpf != null || nome != null) {
            contas = contaReadRepository.findAllClientes(cpf, nome);
        } else {
            contas = contaReadRepository.findAll();
        }

        for (ContaRead conta : contas) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setCpf(conta.getClienteCpf());
            clienteDTO.setNome(conta.getClienteNome());
            clientes.add(clienteDTO);
        }

        return clientes;
    }

    public List<ContaResponseDTO> consultarTop3() {
        List<ContaResponseDTO> top3 = new ArrayList<>();
        Set<ContaRead> contas = contaReadRepository.findTop3();

        for (ContaRead conta : contas) {
            ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
            contaResponseDTO.setNumero(conta.getNumero());
            contaResponseDTO.setLimite(conta.getLimite());
            contaResponseDTO.setSaldo(conta.getSaldo());
            top3.add(contaResponseDTO);
        }

        return top3;
    }

    public List<ContasGerenteDTO> consultarContasPorGerente() {
        List<ContasGerenteDTO> contas = new ArrayList<>();
        List<Object> objects = contaReadRepository.getGerenteContas();

        for (Object object : objects) {
            Object[] obj = (Object[]) object;
            ContasGerenteDTO contasGerenteDTO = new ContasGerenteDTO();
            contasGerenteDTO.setGerenteCpf((String) obj[0]);
            contasGerenteDTO.setGerenteNome((String) obj[1]);
            contasGerenteDTO.setSaldoNegativo((Double) obj[2]);
            contasGerenteDTO.setSaldoPositivo((Double) obj[3]);
            contasGerenteDTO.setNumeroClientes((Long) obj[4]);
            contas.add(contasGerenteDTO);
        }

        return contas;
    }
}
