package com.example.contasservice.projector;

import com.example.contasservice.model.Conta;
import com.example.contasservice.model.ContaRead;
import com.example.contasservice.model.Movimentacao;
import com.example.contasservice.model.MovimentacaoRead;
import com.example.contasservice.repository.read.ContaReadRepository;
import com.example.contasservice.repository.read.MovimentacaoReadRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SyncDatabases {
    // varios métodos que pegam os eventos originados de commands
    // e guardam na base de leitura
    private ContaReadRepository contaReadRepository;
    private MovimentacaoReadRepository movimentacaoReadRepository;

    @Autowired
    SyncDatabases(ContaReadRepository contaReadRepository, MovimentacaoReadRepository movimentacaoReadRepository) {
        this.contaReadRepository = contaReadRepository;
        this.movimentacaoReadRepository = movimentacaoReadRepository;
    }

    @RabbitListener(queues = "contas_service__novo_cliente__database_sync")
    public void syncNovaConta(NovaContaDto contaCriada) {
        ContaRead contaRead = new ContaRead();
        contaRead.setNumero(contaCriada.getNumero());
        contaRead.setLimite(contaCriada.getLimite());
        contaRead.setSaldo(contaCriada.getSaldo());
        contaRead.setStatus(contaCriada.getStatus());
        contaRead.setDataCriacao(contaCriada.getDataCriacao());
        contaRead.setClienteCpf(contaCriada.getClienteCpf());
        contaRead.setClienteNome(contaCriada.getClienteNome());
        contaRead.setGerenteCpf(contaCriada.getGerenteCpf());
        contaRead.setGerenteNome(contaCriada.getGerenteNome());

        contaReadRepository.save(contaRead);
    }

    public void syncAlterarPerfil() {}

    public void syncCreateGerente() {}

    public void syncRemoveGerente() {}

    @RabbitListener(queues = "contas_service__movimentacao__database_sync")
    public void syncMovimentacao(MovimentacaoEvent movimentacaoEvent) {
        ContaRead contaReadOrigem = contaReadRepository.findById(movimentacaoEvent.getContaOrigemId()).get();

        MovimentacaoRead movimentacaoRead = new MovimentacaoRead();
        movimentacaoRead.setValor(movimentacaoEvent.getValor());
        movimentacaoRead.setData(movimentacaoEvent.getData());
        movimentacaoRead.setId(movimentacaoEvent.getId());
        movimentacaoRead.setContaOrigem(contaReadOrigem);

        Long contaDestinoId = movimentacaoEvent.getContaDestinoId() != null ? movimentacaoEvent.getContaDestinoId() : null;
        if (contaDestinoId != null) {
            ContaRead contaReadDestino = contaReadRepository.findById(movimentacaoEvent.getContaDestinoId()).get();
            movimentacaoRead.setContaDestino(contaReadDestino);
        }

        movimentacaoRead.setDirecao(movimentacaoEvent.getDirecao());
        movimentacaoRead.setClienteCpf(movimentacaoEvent.getClienteCpf());
        movimentacaoRead.setTipo(movimentacaoEvent.getTipo());

        movimentacaoReadRepository.save(movimentacaoRead);
    }

    public void syncAprovarConta() {}

    public void syncRejeitarConta() {}
}
