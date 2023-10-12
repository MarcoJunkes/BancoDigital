package com.example.contasservice.repositories;

import com.example.contasservice.dtos.ListagemContaResponseDTO;
import com.example.contasservice.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Conta getByClienteCpf(String cpf);
    int countByGerenteCpf(String cpf);
    @Query(
        "SELECT SUM(CASE WHEN c.saldo > 0 THEN c.saldo ELSE 0 END)" +
        "FROM Conta c WHERE c.gerente.cpf = :cpf")
    Float sumPositiveSaldoByGerenteCpf(String cpf);
    @Query(
        "SELECT SUM(CASE WHEN c.saldo <= 0 THEN c.saldo ELSE 0 END)" +
        "FROM Conta c WHERE c.gerente.cpf = :cpf")
    Float sumNegativeSaldoByGerenteCpf(String cpf);
}
