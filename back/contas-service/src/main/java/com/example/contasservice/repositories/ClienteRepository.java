package com.example.contasservice.repositories;

import com.example.contasservice.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente getByCpf(String cpf);
}
