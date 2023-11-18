package com.example.contasservice.repository.write;

import com.example.contasservice.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GerenteRepository extends JpaRepository<Gerente, String> {
    @Query(
        "SELECT\n" +
        "    SUM(c.numero) as quantidadeClientes,\n" +
        "    g.cpf as gerenteCpf\n" +
        "FROM Gerente g\n" +
        "LEFT JOIN Conta c on g.cpf = c.gerente.cpf\n" +
        "GROUP BY g.cpf\n" +
        "ORDER BY quantidadeClientes DESC LIMIT 1"
    )
    List<Object> getGerenteWithLessClients();

    @Query(
        "SELECT\n" +
        "    SUM(c.numero) as quantidadeClientes,\n" +
        "    g.cpf as gerenteCpf\n" +
        "FROM Gerente g\n" +
        "LEFT JOIN Conta c on g.cpf = c.gerente.cpf\n" +
        "GROUP BY g.cpf\n" +
        "ORDER BY quantidadeClientes DESC"
    )
    List<Object> getGerenteWithLessContas();

    @Query(
        "SELECT\n" +
        "    SUM(c.numero) as quantidadeClientes,\n" +
        "    g.cpf as gerenteCpf\n" +
        "FROM Gerente g\n" +
        "LEFT JOIN Conta c on g.cpf = c.gerente.cpf\n" +
        "GROUP BY g.cpf\n" +
        "ORDER BY quantidadeClientes DESC LIMIT 1"
    )
    List<Object> getGerenteWithMoreContas();
}
