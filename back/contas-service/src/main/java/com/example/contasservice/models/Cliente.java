package com.example.contasservice.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
    @Id
    @Column(name="cpf")
    private String cpf;
    @Column(name="nome")
    private String nome;

    public Cliente() {}

    public Cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
