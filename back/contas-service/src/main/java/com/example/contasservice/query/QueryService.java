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

import java.time.Instant;
import java.util.*;

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
        contaResponseDTO.setGerenteCpf(contaRead.getGerenteCpf());
        contaResponseDTO.setGerenteNome(contaRead.getGerenteNome());
        contaResponseDTO.setClienteCpf(contaRead.getClienteCpf());
        contaResponseDTO.setDataCriacao(contaRead.getDataCriacao());

        return contaResponseDTO;
    }

    public List<ExtratoDTO> consultaExtrato(String cpf, Date startDate, Date endDate) {
        List<ExtratoDTO> extrato = new ArrayList<>();
        Set<MovimentacaoRead> movimentacoes = new HashSet<>();

        try {
            Date now = new Date();
            Instant instant = Instant.parse("2000-01-01T00:00:00.00Z");
            Date beggining = Date.from(instant);

            if (startDate == null) {
                startDate = beggining;
            }
            if (endDate == null) {
                endDate = now;
            }
            movimentacoes = movimentacaoReadRepository.findByDataIsBetweenAndClienteCpf(startDate, endDate, cpf);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

    public List<ContaResponseDTO> consultaClientes(String cpf, String nome, String gerenteCpf, Conta.StatusConta status) {
        List<ContaResponseDTO> clientes = new ArrayList<>();
        List<ContaRead> contas;
        if (cpf != null || nome != null) {
            contas = contaReadRepository.findAllClientes(cpf, nome, gerenteCpf);
        } else if (status != null) {
            contas = contaReadRepository.findByGerenteCpfAndStatus(gerenteCpf, status);
        } else {
            contas = contaReadRepository.findAll();
        }

        for (ContaRead conta : contas) {
            ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
            contaResponseDTO.setGerenteCpf(conta.getGerenteCpf());
            contaResponseDTO.setGerenteNome(conta.getGerenteNome());
            contaResponseDTO.setClienteCpf(conta.getClienteCpf());
            contaResponseDTO.setNumero(conta.getNumero());
            contaResponseDTO.setLimite(conta.getLimite());
            contaResponseDTO.setSaldo(conta.getSaldo());
            contaResponseDTO.setDataCriacao(conta.getDataCriacao());
            clientes.add(contaResponseDTO);
        }

        return clientes;
    }

    public List<ContaResponseDTO> consultarTop3(String gerenteCpf) {
        List<ContaResponseDTO> top3 = new ArrayList<>();
        Set<ContaRead> contas = contaReadRepository.findTop3(gerenteCpf);

        for (ContaRead conta : contas) {
            ContaResponseDTO contaResponseDTO = new ContaResponseDTO();
            contaResponseDTO.setGerenteCpf(conta.getGerenteCpf());
            contaResponseDTO.setGerenteNome(conta.getGerenteNome());
            contaResponseDTO.setClienteCpf(conta.getClienteCpf());
            contaResponseDTO.setNumero(conta.getNumero());
            contaResponseDTO.setLimite(conta.getLimite());
            contaResponseDTO.setSaldo(conta.getSaldo());
            contaResponseDTO.setDataCriacao(conta.getDataCriacao());
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
