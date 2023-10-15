package com.example.contasservice.controllers;

import com.example.contasservice.dtos.NovaContaRequestDTO;
import com.example.contasservice.exceptions.NoGerenteOnDatabase;
import com.example.contasservice.models.Cliente;
import com.example.contasservice.models.Conta;
import com.example.contasservice.services.ClienteService;
import com.example.contasservice.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        try {
            Conta contaCriada = contaService.criarConta(novaConta);

            URI uriContaCriada = new URI("/contas/"+contaCriada.getNumero());

            return ResponseEntity.created(uriContaCriada).build();
        } catch (NoGerenteOnDatabase e) {
            return new ResponseEntity<>("Não há gerentes cadastrados", null, HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/contas")
    public ResponseEntity listarContasPorGerente(@RequestParam("gerente") String gerenteCpf) {
        return ResponseEntity.ok(contaService.getByGerente(gerenteCpf));
    }

    @GetMapping("/contas/{cpf}")
    public ResponseEntity getContaByCliente(@PathVariable String cpf) {
        Conta conta = contaService.getByCliente(cpf);

        if (conta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(conta);
    }

    @GetMapping("/contas/top3")
    public ResponseEntity listarTop3() {
        return ResponseEntity.ok(contaService.getTop3());
    }
}
