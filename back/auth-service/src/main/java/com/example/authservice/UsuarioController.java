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
            // return ResponseEntity.ok().build();
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

/*Código professor
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
public class UsuarioController {

    @PostMapping("/login")
    ResponseEntity<Usuario> login(@RequestBody Login login) {
        if(login.getLogin().equals(login.getSenha())){
            Usuario usu = new Usuario(10, login.getLogin(), login.getLogin(), "XXX", "ADMIN");
            return ResponseEntity.ok().body(usu);
        }
        else {
            return ResponseEntity.status(401).build();
        }
    }
} */