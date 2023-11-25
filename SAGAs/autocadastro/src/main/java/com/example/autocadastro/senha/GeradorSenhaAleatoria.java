package com.example.autocadastro.senha;

import java.security.SecureRandom;

public class GeradorSenhaAleatoria {

    private static final String CARACTERES_PERMITIDOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String gerarSenhaAleatoria(int comprimento) {
        StringBuilder senha = new StringBuilder(comprimento);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < comprimento; i++) {
            int index = random.nextInt(CARACTERES_PERMITIDOS.length());
            senha.append(CARACTERES_PERMITIDOS.charAt(index));
        }

        return senha.toString();
    }
}

