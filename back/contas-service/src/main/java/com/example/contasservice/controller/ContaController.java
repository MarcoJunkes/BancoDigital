package com.example.contasservice.controller;

import com.example.contasservice.command.CommandService;
import com.example.contasservice.dto.DepositoRequestDTO;
import com.example.contasservice.dto.SaqueRequestDTO;
import com.example.contasservice.dto.TransferenciaRequestDTO;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import com.example.contasservice.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContaController {

    private CommandService commandService;
    private QueryService queryService;

    @Autowired
    ContaController(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/contas/{numero}/deposito")
    public ResponseEntity depositar(@PathVariable Long numero, @RequestBody DepositoRequestDTO depositoRequestDto) {
        try {
            commandService.depositar(numero, depositoRequestDto);
            return ResponseEntity.ok().build();
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{numero}/saque")
    public ResponseEntity sacar(@PathVariable Long numero, @RequestBody SaqueRequestDTO saqueRequestDTO) {
        try {
            commandService.sacar(numero, saqueRequestDTO);
            return ResponseEntity.ok().build();
        } catch (ValorNegativoBadRequest e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{numero}/transferencia")
    public ResponseEntity transferir(@PathVariable Long numero, @RequestBody TransferenciaRequestDTO transferenciaRequestDTO) {
        try {
            commandService.transferir(numero, transferenciaRequestDTO);
            return ResponseEntity.ok().build();
        } catch (ValorNegativoBadRequest e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/clientes/{cpf}/aprovar")
    public ResponseEntity aprovarCliente(@PathVariable String cpf) {
        commandService.aprovarConta(cpf);
        return ResponseEntity.ok().build();
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
