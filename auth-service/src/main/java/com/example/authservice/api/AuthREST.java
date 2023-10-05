package com.example.authservice.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AuthREST {
    private static List<Login> usuarios = new ArrayList<>();

    @PostMapping("/login")
    ResponseEntity<Login> login(@RequestBody Login login) {
        Login usuarioLogado = usuarios.stream()
                .filter(u -> u.getEmail().equals(login.getEmail()) && u.getSenha().equals(login.getSenha()))
                .findFirst()
                .orElse(null);

        if (usuarioLogado == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
        return ResponseEntity.created(null).body(usuarioLogado);
    }

    @PostMapping("/cadastro")
    ResponseEntity<Login> cadastro(@RequestBody Login login) {
        boolean exists = usuarios.stream()
                .anyMatch(u -> u.getEmail().equals(login.getEmail()));
        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        usuarios.add(login);
        return ResponseEntity.created(null).body(login);
    }

    static {
        usuarios.add(new Login("cliente@email.com", "cliente@email.com"));
        usuarios.add(new Login("admin@email.com", "admin@email.com"));
        usuarios.add(new Login("gerente@email.com", "gerente@email.com"));
    }
}
