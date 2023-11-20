package com.example.authservice;

import com.example.authservice.dtos.LoginRequestDTO;
import com.example.authservice.dtos.CadastroRequestDTO;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario login(LoginRequestDTO loginRequest) {
        Usuario usuario = usuarioRepository.findOneByEmail(loginRequest.getEmail());
        if (usuario != null) {
            if (passwordEncoder.matches(loginRequest.getSenha(), usuario.getSenha())) {
                return usuario;
            }
        }
        return null;
    }

    public void cadastro(CadastroRequestDTO cadastroRequest) throws Exception {
        String senha = passwordEncoder.encode(cadastroRequest.getSenha());
        Usuario usuario = new Usuario(
                cadastroRequest.getEmail(),
                senha,
                cadastroRequest.getPerfil(),
                cadastroRequest.getCpf());
        try {
            usuarioRepository.save(usuario);
        } catch (DuplicateKeyException e) {
            throw new Exception("Usuário já cadastrado");
        }
    }
}
