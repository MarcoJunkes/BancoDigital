package com.example.contasservice.command;

import com.example.contasservice.dto.DepositoDTO;
import com.example.contasservice.dto.SaqueDTO;
import com.example.contasservice.dto.TransferenciaDTO;
import com.example.contasservice.event.AlteracaoPerfilEvent;
import com.example.contasservice.event.NovaContaEvent;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.model.Cliente;
import com.example.contasservice.model.Conta;
import com.example.contasservice.model.Gerente;
import com.example.contasservice.model.Movimentacao;
import com.example.contasservice.repository.write.ClienteRepository;
import com.example.contasservice.repository.write.ContaRepository;
import com.example.contasservice.repository.write.GerenteRepository;
import com.example.contasservice.repository.write.MovimentacaoRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandService {
    private ContaRepository contaRepository;
    private ClienteRepository clienteRepository;
    private GerenteRepository gerenteRepository;
    private MovimentacaoRepository movimentacaoRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    public CommandService(
            ContaRepository contaRepository,
            ClienteRepository clienteRepository,
            GerenteRepository gerenteRepository,
            MovimentacaoRepository movimentacaoRepository,
            RabbitTemplate rabbitTemplate
    ) {
        this.contaRepository = contaRepository;
        this.clienteRepository = clienteRepository;
        this.gerenteRepository = gerenteRepository;
        this.movimentacaoRepository = movimentacaoRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues="contas_service__novo_cliente")
    public void createConta(NovaContaEvent novaContaEvent) {
        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", new NovaContaEvent());

        List<Object> gerenteRaw = gerenteRepository.getGerenteWithLessClients();
        if (gerenteRaw.isEmpty()) {
            rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync");
        }
        String gerenteCpf = (String) ((Object[]) gerenteRaw.get(0))[1];
        Gerente gerente = gerenteRepository.findById(gerenteCpf).get();

        Cliente novoCliente = new Cliente();
        novoCliente.setCpf(novaContaEvent.getCpf());
        novoCliente.setNome(novaContaEvent.getNome());
        Cliente cliente = clienteRepository.save(novoCliente);

        Conta conta = new Conta();
        conta.setCliente(cliente);
        if (novaContaEvent.getSalario() >= 2000) {
            conta.setLimite(novaContaEvent.getSalario() / 2);
        }

        conta.setGerente(gerente);
        conta.setSaldo(0f);
        conta.setStatus(Conta.StatusConta.PENDENTE_APROVACAO);
        contaRepository.save(conta);

        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", novaContaEvent);
    }

//    @RabbitListener(queues="contas_service__alterar_perfil")
    public void alterarPerfil(AlteracaoPerfilEvent alteracaoPerfilEvent) {}

//    @RabbitListener(queues="contas_service__novo_gerente")
    public void createGerente(Gerente gerente) {}

//    @RabbitListener(queues="contas_service__gerente_excluido")
    public void removeGerente(Gerente gerente) {}

    public void depositar(Long numero, DepositoDTO depositoDTO) throws ContaNotFound {
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }
        conta.setSaldo(conta.getSaldo() + depositoDTO.getValor());
        contaRepository.save(conta);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(Movimentacao.TipoMovimentacao.DEPOSITO);
        movimentacao.setData(new Date());
        movimentacao.setContaOrigem(conta);
        movimentacao.setValor(depositoDTO.getValor());
        movimentacao.setDirecao(Movimentacao.DirecaoMovimentacao.ENTRADA);
        movimentacao.setCliente(conta.getCliente());
        movimentacaoRepository.save(movimentacao);

        // TODO: send event to db sync
//        rabbitTemplate.convertAndSend("contas_service__deposito__database_sync", movimentacao);
    }

    public void sacar(Long numero, SaqueDTO saqueDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }

        if ((saqueDTO.getValor() + conta.getLimite()) < 0) {
            throw new ValorNegativoBadRequest("valor");
        }
        Float novoSaldo = conta.getSaldo() + conta.getLimite() - saqueDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }

        conta.setSaldo(conta.getSaldo() - saqueDTO.getValor());
        contaRepository.save(conta);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(Movimentacao.TipoMovimentacao.SAQUE);
        movimentacao.setData(new Date());
        movimentacao.setContaOrigem(conta);
        movimentacao.setValor(saqueDTO.getValor());
        movimentacao.setDirecao(Movimentacao.DirecaoMovimentacao.SAIDA);
        movimentacao.setCliente(conta.getCliente());
        movimentacaoRepository.save(movimentacao);

        // TODO: send event to db sync
//        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", saque);
    }

    public void transferir(Long contaOrigemId, TransferenciaDTO transferenciaDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta contaOrigem = contaRepository.findById(contaOrigemId).get();
        Conta contaDestino = contaRepository.findById(transferenciaDTO.getContaDestino()).get();
        if (contaOrigem == null || contaDestino == null) {
            throw new ContaNotFound();
        }

        Float novoSaldo = contaOrigem.getSaldo() + contaOrigem.getLimite() - transferenciaDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferenciaDTO.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transferenciaDTO.getValor());
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Movimentacao movimentacaoSaida = new Movimentacao();
        movimentacaoSaida.setTipo(Movimentacao.TipoMovimentacao.TRANSFERENCIA);
        movimentacaoSaida.setData(new Date());
        movimentacaoSaida.setContaOrigem(contaOrigem);
        movimentacaoSaida.setContaDestino(contaDestino);
        movimentacaoSaida.setValor(transferenciaDTO.getValor());
        movimentacaoSaida.setDirecao(Movimentacao.DirecaoMovimentacao.SAIDA);
        movimentacaoSaida.setCliente(contaOrigem.getCliente());
        movimentacaoRepository.save(movimentacaoSaida);

        Movimentacao movimentacaoEntrada = new Movimentacao();
        movimentacaoEntrada.setTipo(Movimentacao.TipoMovimentacao.TRANSFERENCIA);
        movimentacaoEntrada.setData(new Date());
        movimentacaoEntrada.setContaOrigem(contaDestino);
        movimentacaoEntrada.setContaDestino(contaOrigem);
        movimentacaoEntrada.setValor(transferenciaDTO.getValor());
        movimentacaoEntrada.setDirecao(Movimentacao.DirecaoMovimentacao.ENTRADA);
        movimentacaoEntrada.setCliente(contaDestino.getCliente());
        movimentacaoRepository.save(movimentacaoEntrada);

        // TODO: send event to db sync
//        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", transferencia);
    }

    public void aprovarConta(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.ATIVA);
        contaRepository.save(conta);

        // TODO: send event to db sync
//        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", transferencia);
    }

    public void rejeitarConta(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.REJEITADA);
        contaRepository.save(conta);

        // TODO: send event to db sync
//        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", transferencia);
    }
}
