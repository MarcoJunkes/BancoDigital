package com.example.contasservice.controller;

import com.example.contasservice.command.CommandService;
import com.example.contasservice.dto.DepositoRequestDTO;
import com.example.contasservice.dto.SaqueRequestDTO;
import com.example.contasservice.dto.TransferenciaRequestDTO;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.query.QueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaController {
    private CommandService commandService;
    private QueryService queryService;

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;

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

    /*@PostMapping("/clientes/{cpf}/aprovar")
    public ResponseEntity aprovarCliente(@PathVariable String cpf) {
        commandService.aprovarConta(cpf);
        return ResponseEntity.ok().build();
    }*/

    @RabbitListener(queues = "service_conta__request_aprovar_conta")
    public void aprovarCliente(String msg) throws JsonMappingException, JsonProcessingException {
        String cpf = objectMapper.readValue(msg, String.class);
        commandService.aprovarConta(cpf);
        String json = objectMapper.writeValueAsString(cpf);
        rabbitTemplate.convertAndSend("service_conta__response_aprovar_conta", json);
    }

    @PostMapping("/clientes/{cpf}/rejeitar")
    public ResponseEntity rejeitarCliente(@PathVariable String cpf) {
        commandService.rejeitarConta(cpf);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contas/{cpf}/extrato")
    public ResponseEntity extrato(@PathVariable String cpf) {
        try {
            return ResponseEntity.ok(queryService.consultaExtrato(cpf));
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
    public ResponseEntity top3() {
        try {
            return ResponseEntity.ok(queryService.consultarTop3());
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity getClientes(
            @RequestParam(value = "cpf", required = false) String cpf,
            @RequestParam(value = "nome", required = false) String nome) {
        try {
            return ResponseEntity.ok(queryService.consultaClientes(cpf, nome));
        } catch (Exception e) {
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
