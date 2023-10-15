package com.example.contasservice.services;

import com.example.contasservice.dtos.ListagemClienteDTO;
import com.example.contasservice.dtos.ListagemContaResponseDTO;
import com.example.contasservice.dtos.NovaContaRequestDTO;
import com.example.contasservice.exceptions.NoGerenteOnDatabase;
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

    public Conta criarConta(NovaContaRequestDTO novaContaRequest) throws NoGerenteOnDatabase {
        List<Object> gerenteRaw = gerenteRepository.getGerenteWithLessClients();
        if (gerenteRaw.size() == 0) {
            throw new NoGerenteOnDatabase();
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
}
