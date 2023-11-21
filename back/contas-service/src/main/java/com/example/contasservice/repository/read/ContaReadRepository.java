package com.example.contasservice.repository.read;

import com.example.contasservice.model.ContaRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ContaReadRepository extends JpaRepository<ContaRead, Long> {
    ContaRead findContaReadByClienteCpf(String cpf);
    @Query(value = "SELECT * FROM read.conta ORDER BY saldo DESC LIMIT 3", nativeQuery = true)
    Set<ContaRead> findTop3();
    @Query(value = "SELECT * FROM read.conta WHERE cliente_cpf = :cpf OR cliente_nome = :nome", nativeQuery = true)
    List<ContaRead> findAllClientes(@Param("cpf") String cpf, @Param("nome") String nome);
    @Query(
        "SELECT " +
        "c.gerenteCpf, " +
        "SUM(CASE WHEN c.saldo <= 0 THEN c.saldo ELSE 0 END) as saldoNegativo, " +
        "SUM(CASE WHEN c.saldo > 0 THEN c.saldo ELSE 0 END) as saldoPositivo, " +
        "COUNT(c.numero) as numeroClientes " +
        "FROM ContaRead c " +
        "GROUP BY c.gerenteCpf")
    List<Object> getGerenteContas();
}