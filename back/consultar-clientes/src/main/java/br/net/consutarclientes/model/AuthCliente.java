package br.net.consutarclientes.model;

import java.io.Serializable;

public class AuthCliente implements Serializable{
    private Long id;
    private String email;
    private String cpf;

    public AuthCliente(){

    }

    public AuthCliente(Long id, String email, String cpf){
        this.id = id;
        this.email = email;
        this.cpf = cpf;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getCPF(){
        return cpf;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }
}
