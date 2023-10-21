package com.example.contasservice.controllers;

import com.example.contasservice.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity listAll() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @PostMapping("/clientes/{cpf}/aprovar")
    public ResponseEntity aprovarCliente(@PathVariable String cpf) {
        clienteService.aprovarCliente(cpf);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/clientes/{cpf}/rejeitar")
    public ResponseEntity rejeitarCliente(@PathVariable String cpf) {
        clienteService.rejeitarCliente(cpf);
        return ResponseEntity.ok().build();
    }
}
