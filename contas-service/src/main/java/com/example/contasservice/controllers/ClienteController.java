package com.example.contasservice.controllers;

import com.example.contasservice.models.Cliente;
import com.example.contasservice.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ResponseEntity listAll() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }
}
