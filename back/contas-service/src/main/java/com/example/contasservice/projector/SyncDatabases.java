package com.example.contasservice.projector;

import com.example.contasservice.model.Conta;
import com.example.contasservice.model.ContaRead;
import com.example.contasservice.repository.read.ContaReadRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncDatabases {
    // varios métodos que pegam os eventos originados de commands
    // e guardam na base de leitura
    @Autowired
    private ContaReadRepository contaReadRepository;

    @RabbitListener(queues = "contas_service__novo_cliente__database_sync")
    public void syncNovaConta(Conta contaCriada) {
        ContaRead contaRead = new ContaRead();
        contaRead.setNumero(contaCriada.getNumero());
        contaRead.setSaldo(contaCriada.getSaldo());
        contaReadRepository.save(contaRead);
    }

    public void syncAlterarPerfil() {}

    public void syncCreateGerente() {}

    public void syncRemoveGerente() {}

    public void syncDepositar() {}

    public void syncSacar() {}

    public void syncTransferir() {}

    public void syncAprovarConta() {}

    public void syncRejeitarConta() {}
}
