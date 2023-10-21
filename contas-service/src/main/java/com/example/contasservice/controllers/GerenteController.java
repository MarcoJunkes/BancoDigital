package com.example.contasservice.controllers;

import com.example.contasservice.dtos.NovoGerenteRequestDTO;
import com.example.contasservice.services.GerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GerenteController {
    @Autowired
    private GerenteService gerenteService;

    @PostMapping("/gerentes")
    public ResponseEntity createGerente(@RequestBody NovoGerenteRequestDTO novoGerenteRequestDTO) {
        gerenteService.createGerente(novoGerenteRequestDTO);
        return ResponseEntity.ok().build();
    }
}
