package br.net.crudgerente.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.net.crudgerente.model.Gerente;

public interface GerenteRepository extends JpaRepository<Gerente, Long>{

    
}
