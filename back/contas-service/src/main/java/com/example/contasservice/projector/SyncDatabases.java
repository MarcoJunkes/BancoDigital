package com.example.contasservice.projector;

import com.example.contasservice.model.ContaRead;
import com.example.contasservice.model.MovimentacaoRead;
import com.example.contasservice.repository.read.ContaReadRepository;
import com.example.contasservice.repository.read.MovimentacaoReadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class SyncDatabases {
    // varios métodos que pegam os eventos originados de commands
    // e guardam na base de leitura
    private ContaReadRepository contaReadRepository;
    private MovimentacaoReadRepository movimentacaoReadRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncDatabases.class);


    @Autowired
    SyncDatabases(ContaReadRepository contaReadRepository, MovimentacaoReadRepository movimentacaoReadRepository) {
        this.contaReadRepository = contaReadRepository;
        this.movimentacaoReadRepository = movimentacaoReadRepository;
    }

    @RabbitListener(queues = "contas_service__novo_cliente__database_sync")
    public void syncNovaConta(NovaContaDto contaCriada) {
        LOGGER.info("started syncNovaConta", contaCriada);

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

        LOGGER.info("finished syncNovaConta");
    }

    public void syncAlterarPerfil() {}

    public void syncCreateGerente() {}

    public void syncRemoveGerente() {}

    @RabbitListener(queues = "contas_service__movimentacao__database_sync")
    public void syncMovimentacao(MovimentacaoEvent movimentacaoEvent) {
        LOGGER.info("started syncMovimentacao", movimentacaoEvent);

        ContaRead contaReadOrigem = contaReadRepository.findById(movimentacaoEvent.getContaOrigemId()).get();
        contaReadOrigem.setSaldo(movimentacaoEvent.getContaOrigemSaldo());
        contaReadRepository.save(contaReadOrigem);

        MovimentacaoRead movimentacaoRead = new MovimentacaoRead();
        movimentacaoRead.setValor(movimentacaoEvent.getValor());
        movimentacaoRead.setData(movimentacaoEvent.getData());
        movimentacaoRead.setId(movimentacaoEvent.getId());
        movimentacaoRead.setContaOrigem(contaReadOrigem);

        Long contaDestinoId = movimentacaoEvent.getContaDestinoId() != null ? movimentacaoEvent.getContaDestinoId() : null;
        if (contaDestinoId != null) {
            ContaRead contaReadDestino = contaReadRepository.findById(movimentacaoEvent.getContaDestinoId()).get();
            contaReadDestino.setSaldo(movimentacaoEvent.getContaDestinoSaldo());
            contaReadRepository.save(contaReadDestino);

            movimentacaoRead.setContaDestino(contaReadDestino);
        }

        movimentacaoRead.setDirecao(movimentacaoEvent.getDirecao());
        movimentacaoRead.setClienteCpf(movimentacaoEvent.getClienteCpf());
        movimentacaoRead.setTipo(movimentacaoEvent.getTipo());

        movimentacaoReadRepository.save(movimentacaoRead);

        LOGGER.info("finished syncMovimentacao");
    }

    @RabbitListener(queues = "contas_service__conta__database_sync")
    public void syncConta(ContaEvent contaEvent) {
        ContaRead contaRead = contaReadRepository.findById(contaEvent.getNumero()).get();
        contaRead.setNumero(contaEvent.getNumero());
        contaRead.setStatus(contaEvent.getStatus());
        contaRead.setDataCriacao(contaEvent.getDataCriacao());
        contaRead.setSaldo(contaEvent.getSaldo());
        contaRead.setLimite(contaEvent.getLimite());
        contaRead.setClienteCpf(contaEvent.getClienteCpf());
        contaRead.setClienteNome(contaEvent.getClienteNome());
        contaRead.setGerenteCpf(contaEvent.getGerenteCpf());
        contaRead.setGerenteNome(contaEvent.getGerenteNome());

        contaReadRepository.save(contaRead);
    }
}
