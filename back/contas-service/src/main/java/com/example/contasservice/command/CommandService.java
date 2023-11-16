package com.example.contasservice.command;

import com.example.contasservice.dto.DepositoRequestDTO;
import com.example.contasservice.dto.SaqueRequestDTO;
import com.example.contasservice.dto.TransferenciaRequestDTO;
import com.example.contasservice.event.AlteracaoPerfilEvent;
import com.example.contasservice.event.NovaContaEvent;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.model.Cliente;
import com.example.contasservice.model.Conta;
import com.example.contasservice.model.Gerente;
import com.example.contasservice.model.Movimentacao;
import com.example.contasservice.projector.ContaEvent;
import com.example.contasservice.projector.MovimentacaoEvent;
import com.example.contasservice.projector.NovaContaDto;
import com.example.contasservice.repository.write.ClienteRepository;
import com.example.contasservice.repository.write.ContaRepository;
import com.example.contasservice.repository.write.GerenteRepository;
import com.example.contasservice.repository.write.MovimentacaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandService.class);


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
        LOGGER.info("started createConta", novaContaEvent);

        List<Object> gerenteRaw = gerenteRepository.getGerenteWithLessClients();
        if (gerenteRaw.isEmpty()) {
            rabbitTemplate.convertAndSend("contas_service__novo_cliente__response", new NovaContaEvent());
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
        Conta contaSaved = contaRepository.save(conta);
        NovaContaDto novaContaDto = new NovaContaDto();
        novaContaDto.setNumero(contaSaved.getNumero());
        novaContaDto.setLimite(contaSaved.getLimite());
        novaContaDto.setSaldo(contaSaved.getSaldo());
        novaContaDto.setDataCriacao(contaSaved.getDataCriacao());
        novaContaDto.setStatus(contaSaved.getStatus());
        novaContaDto.setGerenteCpf(contaSaved.getGerente().getCpf());
        novaContaDto.setGerenteNome(contaSaved.getGerente().getNome());
        novaContaDto.setClienteCpf(contaSaved.getCliente().getCpf());
        novaContaDto.setClienteNome(contaSaved.getCliente().getNome());

        rabbitTemplate.convertAndSend("contas_service__novo_cliente__database_sync", novaContaDto);
        rabbitTemplate.convertAndSend("contas_service__novo_cliente__response", novaContaDto);

        LOGGER.info("finished createConta");
    }

//    @RabbitListener(queues="contas_service__alterar_perfil")
    public void alterarPerfil(AlteracaoPerfilEvent alteracaoPerfilEvent) {}

//    @RabbitListener(queues="contas_service__novo_gerente")
    public void createGerente(Gerente gerente) {}

//    @RabbitListener(queues="contas_service__gerente_excluido")
    public void removeGerente(Gerente gerente) {}

    public void depositar(Long numero, DepositoRequestDTO depositoRequestDTO) throws ContaNotFound {
        // TODO: exception if conta is not approved
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }
        conta.setSaldo(conta.getSaldo() + depositoRequestDTO.getValor());
        contaRepository.save(conta);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(Movimentacao.TipoMovimentacao.DEPOSITO);
        movimentacao.setData(new Date());
        movimentacao.setContaOrigem(conta);
        movimentacao.setValor(depositoRequestDTO.getValor());
        movimentacao.setDirecao(Movimentacao.DirecaoMovimentacao.ENTRADA);
        movimentacao.setCliente(conta.getCliente());
        movimentacaoRepository.save(movimentacao);

        // send event to db sync
        sendMovimentacaoSyncEvent(movimentacao);
    }

    public void sacar(Long numero, SaqueRequestDTO saqueRequestDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta conta = contaRepository.findById(numero).get();
        if (conta == null) {
            throw new ContaNotFound();
        }

        if ((saqueRequestDTO.getValor() + conta.getLimite()) < 0) {
            throw new ValorNegativoBadRequest("valor");
        }
        Float novoSaldo = conta.getSaldo() + conta.getLimite() - saqueRequestDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }

        conta.setSaldo(conta.getSaldo() - saqueRequestDTO.getValor());
        contaRepository.save(conta);

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setTipo(Movimentacao.TipoMovimentacao.SAQUE);
        movimentacao.setData(new Date());
        movimentacao.setContaOrigem(conta);
        movimentacao.setValor(saqueRequestDTO.getValor());
        movimentacao.setDirecao(Movimentacao.DirecaoMovimentacao.SAIDA);
        movimentacao.setCliente(conta.getCliente());
        movimentacaoRepository.save(movimentacao);

        // send event to db sync
        sendMovimentacaoSyncEvent(movimentacao);
    }

    public void transferir(Long contaOrigemId, TransferenciaRequestDTO transferenciaRequestDTO) throws ContaNotFound, ValorNegativoBadRequest {
        Conta contaOrigem = contaRepository.findById(contaOrigemId).get();
        Conta contaDestino = contaRepository.findById(transferenciaRequestDTO.getContaDestino()).get();
        if (contaOrigem == null || contaDestino == null) {
            throw new ContaNotFound();
        }

        Float novoSaldo = contaOrigem.getSaldo() + contaOrigem.getLimite() - transferenciaRequestDTO.getValor();
        if (novoSaldo < 0) {
            throw new ValorNegativoBadRequest("saldo");
        }
        contaOrigem.setSaldo(contaOrigem.getSaldo() - transferenciaRequestDTO.getValor());
        contaDestino.setSaldo(contaDestino.getSaldo() + transferenciaRequestDTO.getValor());
        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestino);

        Movimentacao movimentacaoSaida = new Movimentacao();
        movimentacaoSaida.setTipo(Movimentacao.TipoMovimentacao.TRANSFERENCIA);
        movimentacaoSaida.setData(new Date());
        movimentacaoSaida.setContaOrigem(contaOrigem);
        movimentacaoSaida.setContaDestino(contaDestino);
        movimentacaoSaida.setValor(transferenciaRequestDTO.getValor());
        movimentacaoSaida.setDirecao(Movimentacao.DirecaoMovimentacao.SAIDA);
        movimentacaoSaida.setCliente(contaOrigem.getCliente());
        movimentacaoRepository.save(movimentacaoSaida);

        sendMovimentacaoSyncEvent(movimentacaoSaida);

        Movimentacao movimentacaoEntrada = new Movimentacao();
        movimentacaoEntrada.setTipo(Movimentacao.TipoMovimentacao.TRANSFERENCIA);
        movimentacaoEntrada.setData(new Date());
        movimentacaoEntrada.setContaOrigem(contaDestino);
        movimentacaoEntrada.setContaDestino(contaOrigem);
        movimentacaoEntrada.setValor(transferenciaRequestDTO.getValor());
        movimentacaoEntrada.setDirecao(Movimentacao.DirecaoMovimentacao.ENTRADA);
        movimentacaoEntrada.setCliente(contaDestino.getCliente());
        movimentacaoRepository.save(movimentacaoEntrada);

        sendMovimentacaoSyncEvent(movimentacaoEntrada);
    }

    public void aprovarConta(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.ATIVA);
        contaRepository.save(conta);

        sendContaSyncEvent(conta);
    }

    public void rejeitarConta(String cpf) {
        Conta conta = contaRepository.getByClienteCpf(cpf);
        conta.setStatus(Conta.StatusConta.REJEITADA);
        contaRepository.save(conta);

        sendContaSyncEvent(conta);
    }

    private void sendMovimentacaoSyncEvent (Movimentacao movimentacao) {
        LOGGER.info("started sendMovimentacaoEvent", movimentacao);

        MovimentacaoEvent movimentacaoEvent = new MovimentacaoEvent();
        movimentacaoEvent.setId(movimentacao.getId());
        movimentacaoEvent.setClienteCpf(movimentacao.getCliente().getCpf());

        movimentacaoEvent.setContaOrigemId(movimentacao.getContaOrigem().getNumero());
        movimentacaoEvent.setContaOrigemSaldo(movimentacao.getContaOrigem().getSaldo());

        movimentacaoEvent.setContaDestinoId(movimentacao.getContaDestino() != null ? movimentacao.getContaDestino().getNumero() : null);
        movimentacaoEvent.setContaDestinoSaldo(movimentacao.getContaDestino() != null ? movimentacao.getContaDestino().getSaldo() : null);

        movimentacaoEvent.setDirecao(movimentacao.getDirecao());
        movimentacaoEvent.setTipo(movimentacao.getTipo());
        movimentacaoEvent.setData(movimentacao.getData());
        movimentacaoEvent.setValor(movimentacao.getValor());
        rabbitTemplate.convertAndSend("contas_service__movimentacao__database_sync", movimentacaoEvent);

        LOGGER.info("finished sendMovimentacaoEvent");
    }

    private void sendContaSyncEvent (Conta conta) {
        LOGGER.info("started sendContaSyncEvent", conta);

        ContaEvent contaEvent = new ContaEvent();
        contaEvent.setNumero(conta.getNumero());
        contaEvent.setStatus(conta.getStatus());
        contaEvent.setDataCriacao(conta.getDataCriacao());
        contaEvent.setSaldo(conta.getSaldo());
        contaEvent.setLimite(conta.getLimite());
        contaEvent.setClienteCpf(conta.getCliente().getCpf());
        contaEvent.setClienteNome(conta.getCliente().getNome());
        contaEvent.setGerenteCpf(conta.getGerente().getCpf());
        contaEvent.setGerenteNome(conta.getGerente().getNome());
        rabbitTemplate.convertAndSend("contas_service__conta__database_sync", contaEvent);

        LOGGER.info("finished sendContaSyncEvent");
    }
}
