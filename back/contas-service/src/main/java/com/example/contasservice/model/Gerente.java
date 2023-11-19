package com.example.contasservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="gerente", schema = "write")
public class Gerente implements Serializable {
    @Id
    @Column(name="cpf")
    private String cpf;
    @Column(name="nome")
    private String nome;
    @OneToMany(mappedBy="gerente")
    private Set<Conta> contas;

    public Gerente() {}

    public Gerente(String cpf, String nome, Set<Conta> contas) {
        this.cpf = cpf;
        this.nome = nome;
        this.contas = contas;
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

    public Set<Conta> getContas() {
        return contas;
    }

    public void setContas(Set<Conta> contas) {
        this.contas = contas;
    }
}
