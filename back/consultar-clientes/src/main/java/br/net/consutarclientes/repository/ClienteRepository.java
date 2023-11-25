package br.net.consutarclientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.consutarclientes.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findByCPF(String cpf);
}
