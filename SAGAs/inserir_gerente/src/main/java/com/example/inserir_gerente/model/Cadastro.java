package com.example.inserir_gerente.model;

import java.io.Serializable;

public class Cadastro implements Serializable{
    private String email;
    private String senha;
    private String perfil;

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getPerfil(){
        return perfil;
    }

    public void setPerfil(String perfil){
        this.perfil = perfil;
    }
}
