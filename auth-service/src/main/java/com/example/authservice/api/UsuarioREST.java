package com.example.authservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioREST {
    private static List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("usuarios/")
    public List<Usuario> listarTodos() {
        return usuarios;
    }

    static {
        usuarios.add(new Usuario(1, "Matheus"));
    }
}
