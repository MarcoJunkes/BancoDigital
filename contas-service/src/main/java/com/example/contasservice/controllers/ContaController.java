package com.example.contasservice.controllers;

import com.example.contasservice.dtos.NovaContaRequestDTO;
import com.example.contasservice.models.Cliente;
import com.example.contasservice.models.Conta;
import com.example.contasservice.services.ClienteService;
import com.example.contasservice.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;
    @Autowired
    private ClienteService clienteService;

    @PostMapping("/contas")
    public ResponseEntity criarConta(@RequestBody NovaContaRequestDTO novaConta) {
        Cliente cliente = clienteService.getByCpf(novaConta.getCpf());
        if (cliente != null) {
            return new ResponseEntity<>("Cliente já possui conta", null, HttpStatus.CONFLICT);
        }

        Conta contaCriada = contaService.criarConta(novaConta);

        URI uriContaCriada = null;
        try {
            uriContaCriada = new URI("/contas/"+contaCriada.getNumero());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.created(uriContaCriada).build();
    }

}
