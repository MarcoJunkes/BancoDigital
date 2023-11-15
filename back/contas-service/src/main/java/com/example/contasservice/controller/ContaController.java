package com.example.contasservice.controller;

import com.example.contasservice.command.CommandService;
import com.example.contasservice.dto.DepositoDTO;
import com.example.contasservice.dto.SaqueDTO;
import com.example.contasservice.dto.TransferenciaDTO;
import com.example.contasservice.exceptions.ContaNotFound;
import com.example.contasservice.exceptions.ValorNegativoBadRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContaController {

    private CommandService commandService;

    @Autowired
    ContaController(CommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping("/contas/{numero}/deposito")
    public ResponseEntity depositar(@PathVariable Long numero, @RequestBody DepositoDTO depositoDto) {
        try {
            commandService.depositar(numero, depositoDto);
            return ResponseEntity.ok().build();
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{numero}/saque")
    public ResponseEntity sacar(@PathVariable Long numero, @RequestBody SaqueDTO saqueDTO) {
        try {
            commandService.sacar(numero, saqueDTO);
            return ResponseEntity.ok().build();
        } catch (ValorNegativoBadRequest e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (ContaNotFound e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/contas/{numero}/transferencia")
    public ResponseEntity transferir(@PathVariable Long numero, @RequestBody TransferenciaDTO transferenciaDTO) {
        try {
            commandService.transferir(numero, transferenciaDTO);
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
}
