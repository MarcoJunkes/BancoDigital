package com.example.contasservice.controller;

import com.example.contasservice.command.CommandService;
import com.example.contasservice.dto.DepositoRequestDTO;
import com.example.contasservice.dto.SaqueRequestDTO;
import com.example.contasservice.dto.TransferenciaRequestDTO;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.model.Conta;
import com.example.contasservice.query.QueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ContaController {
    private CommandService commandService;
    private QueryService queryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(ContaController.class);

    ContaController(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/contas/{clienteCpf}/deposito")
    public ResponseEntity depositar(@PathVariable String clienteCpf, @RequestBody DepositoRequestDTO depositoRequestDto) {
        try {
            commandService.depositar(clienteCpf, depositoRequestDto);
            return ResponseEntity.ok().build();
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{clienteCpf}/saque")
    public ResponseEntity sacar(@PathVariable String clienteCpf, @RequestBody SaqueRequestDTO saqueRequestDTO) {
        try {
            commandService.sacar(clienteCpf, saqueRequestDTO);
            return ResponseEntity.ok().build();
        } catch (ValorNegativoBadRequest e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{clienteCpf}/transferencia")
    public ResponseEntity transferir(
            @PathVariable String clienteCpf,
            @RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        try {
            commandService.transferir(clienteCpf, transferenciaRequestDTO);
            return ResponseEntity.ok().build();
        } catch (ValorNegativoBadRequest e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @RabbitListener(queues = "service_conta__request_aprovar_conta")
    public void aprovarCliente(String msg) throws JsonMappingException, JsonProcessingException {
        String cpf = objectMapper.readValue(msg, String.class);
        commandService.aprovarConta(cpf);
        String json = objectMapper.writeValueAsString(cpf);
        rabbitTemplate.convertAndSend("service_conta__response_aprovar_conta", json);
    }

    @RabbitListener(queues = "service_conta__request_rejeitar_conta")
    public void rejeitarCliente(String msg) throws JsonMappingException, JsonProcessingException{
        String cpf = objectMapper.readValue(msg, String.class);
        commandService.rejeitarConta(cpf);
        String json = objectMapper.writeValueAsString(cpf);
        rabbitTemplate.convertAndSend("service_conta__response_rejeitar_conta", json);
    }

    @GetMapping("/contas/{cpf}/extrato")
    public ResponseEntity extrato(
            @PathVariable String cpf,
            @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date dataInicial,
            @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX") Date dataFinal) {
        try {
            return ResponseEntity.ok(queryService.consultaExtrato(cpf, dataInicial, dataFinal));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("contas/{cpf}")
    public ResponseEntity getConta(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(queryService.consultarPorCpf(cpf));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contas/top3")
    public ResponseEntity top3(@RequestParam("gerenteCpf") String gerenteCpf) {
        try {
            return ResponseEntity.ok(queryService.consultarTop3(gerenteCpf));
        } catch (Exception e) {

            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity getClientes(
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "gerenteCpf", required = false) String gerenteCpf,
            @RequestParam(value = "status", required = false) Conta.StatusConta status) {
        try {
            return ResponseEntity.ok(queryService.consultaClientes(cpf, nome, gerenteCpf, status));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/contas/gerentes")
    public ResponseEntity getContasGerentes() {
        try {
            return ResponseEntity.ok(queryService.consultarContasPorGerente());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
