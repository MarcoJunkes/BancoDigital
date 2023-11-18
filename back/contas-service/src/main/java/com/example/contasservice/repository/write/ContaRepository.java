package com.example.contasservice.repository.write;

import com.example.contasservice.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta getByClienteCpf(String cpf);
    List<Conta> getByGerenteCpf(String cpf);
    Conta getFirstByGerente_Cpf(String cpf);
}
