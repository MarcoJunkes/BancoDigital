package br.net.crudgerente.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="gerente")
public class Gerente implements Serializable {
    @Id
    @GeneratedValue(generator = "id_sequence_gerente")
    @SequenceGenerator(name = "id_sequence_gerente", sequenceName = "gerente_id_sequence", allocationSize = 1)
    @Column(name="id")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="cpf")
    private String cpf;
    @Column(name="telefone")
    private String telefone;

    public Gerente(){
        super();
    }

    public Gerente(Long id, String nome, String email, String cpf, String telefone){
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
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

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
}
