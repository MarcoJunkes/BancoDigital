package com.example.contasservice.services;

import com.example.contasservice.dtos.NovoGerenteRequestDTO;
import com.example.contasservice.models.Conta;
import com.example.contasservice.models.Gerente;
import com.example.contasservice.repositories.ContaRepository;
import com.example.contasservice.repositories.GerenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GerenteService {
    @Autowired
    private GerenteRepository gerenteRepository;
    @Autowired
    private ContaRepository contaRepository;

    public void createGerente(NovoGerenteRequestDTO gerenteRequestDTO) {
        Gerente gerente = new Gerente();
        gerente.setCpf(gerenteRequestDTO.getCpf());
        gerente.setNome(gerenteRequestDTO.getNome());
        gerenteRepository.save(gerente);

        List<Object> gerenteComMenosContasRaw = gerenteRepository.getGerenteWithLessContas();
        if (gerenteComMenosContasRaw.size() == 1) {
            return;
        }
        String gerenteCpf = (String) ((Object[]) gerenteComMenosContasRaw.get(0))[1];

        List<Conta> contasDoGerenteComMenosContas = contaRepository.getByGerenteCpf(gerenteCpf);
        Conta conta = contasDoGerenteComMenosContas.get(0);

        conta.setGerente(gerente);
    }
}
