package com.example.authservice;

import com.example.authservice.dtos.LoginRequestDTO;
import com.example.authservice.dtos.CadastroRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioService.login(loginRequest);
        if (usuario != null) {
            return ResponseEntity.ok().body(usuario);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping("/cadastro")
    public ResponseEntity cadastro(@RequestBody CadastroRequestDTO cadastroRequest) {
        try {
            usuarioService.cadastro(cadastroRequest);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        return ResponseEntity.created(null).build();
    }
}
