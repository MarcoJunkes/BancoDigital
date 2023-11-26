package com.example.contasservice.repository.read;

import com.example.contasservice.model.Conta;
import com.example.contasservice.model.ContaRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface ContaReadRepository extends JpaRepository<ContaRead, Long> {
    ContaRead findContaReadByClienteCpf(String cpf);
    @Query(value = "SELECT * FROM read.conta WHERE gerente_cpf = :gerenteCpf ORDER BY saldo DESC LIMIT 3", nativeQuery = true)
    Set<ContaRead> findTop3(@Param("gerenteCpf") String gerenteCpf);
    @Query(value = "SELECT * FROM read.conta WHERE cliente_cpf = :cpf OR cliente_nome = :nome AND gerente_cpf = :gerenteCpf", nativeQuery = true)
    List<ContaRead> findAllClientes(
            @Param("cpf") String cpf,
            @Param("nome") String nome,
            @Param("gerenteCpf") String gerenteCpf);
    List<ContaRead> findByGerenteCpfAndStatus(String gerenteCpf, Conta.StatusConta status);
    @Query(
        "SELECT " +
        "c.gerenteCpf, " +
        "c.gerenteNome, " +
        "SUM(CASE WHEN c.saldo <= 0 THEN c.saldo ELSE 0 END) as saldoNegativo, " +
        "SUM(CASE WHEN c.saldo > 0 THEN c.saldo ELSE 0 END) as saldoPositivo, " +
        "COUNT(c.numero) as numeroClientes " +
        "FROM ContaRead c " +
        "GROUP BY c.gerenteCpf, c.gerenteNome")
    List<Object> getGerenteContas();
}
