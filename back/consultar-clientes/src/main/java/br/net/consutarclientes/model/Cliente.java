package br.net.consutarclientes.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
    @Id
    @Column(name="id")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="cpf")
    private String cpf;
    @Column(name="cidade")
    private String cidade;
    @Column(name="estado")
    private String estado;
    @Column(name="saldo")
    private float saldo;

    public Cliente(){
        super();
    }

    public Cliente(Long id, String nome, String cpf, String cidade, String estado, float saldo){
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cidade = cidade;
        this.estado = estado;
        this.saldo = saldo;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCPF(){
        return cpf;
    }

    public void setCPF(String cpf){
        this.cpf = cpf;
    }

    public String getCidade(){
        return cidade;
    }
    
    public void setCidade(String cidade){
        this.cidade = cidade;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public float getSaldo(){
        return saldo;
    }

    public void setSaldo(float saldo){
        this.saldo = saldo;
    }
}
