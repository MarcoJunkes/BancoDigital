package com.example.contasservice.repository.read;

import com.example.contasservice.model.ContaRead;
import com.example.contasservice.model.MovimentacaoRead;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface MovimentacaoReadRepository extends JpaRepository<MovimentacaoRead, Long> {
    Set<MovimentacaoRead> findByClienteCpf(String cpf);

}
