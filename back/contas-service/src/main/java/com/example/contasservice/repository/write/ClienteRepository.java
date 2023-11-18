package com.example.contasservice.repository.write;

import com.example.contasservice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Cliente getByCpf(String cpf);
}
