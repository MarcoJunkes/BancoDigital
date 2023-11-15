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

        System.out.println("deu boa");
        contaReadRepository.save(contaRead);
    }

    public void syncAlterarPerfil() {}

    public void syncCreateGerente() {}

    public void syncRemoveGerente() {}

    @RabbitListener(queues = "contas_service__movimentacao__database_sync")
    public void syncMovimentacao(MovimentacaoEvent movimentacaoEvent) {
        ContaRead contaReadOrigem = contaReadRepository.findById(movimentacaoEvent.getMovimentacao().getContaOrigem().getNumero()).get();
        ContaRead contaReadDestino = contaReadRepository.findById(movimentacaoEvent.getMovimentacao().getContaDestino().getNumero()).get();
        Movimentacao movimentacao = movimentacaoEvent.getMovimentacao();
        MovimentacaoRead movimentacaoRead = new MovimentacaoRead();
        movimentacaoRead.setValor(movimentacao.getValor());
        movimentacaoRead.setData(movimentacao.getData());
        movimentacaoRead.setId(movimentacao.getId());
        movimentacaoRead.setContaOrigem(contaReadOrigem);
        movimentacaoRead.setContaDestino(contaReadDestino);
        movimentacaoRead.setDirecao(movimentacao.getDirecao());
        movimentacaoRead.setClienteCpf(movimentacao.getCliente().getCpf());
        movimentacaoRead.setTipo(movimentacao.getTipo());
        movimentacaoReadRepository.save(movimentacaoRead);
    }

    public void syncAprovarConta() {}

    public void syncRejeitarConta() {}
}
