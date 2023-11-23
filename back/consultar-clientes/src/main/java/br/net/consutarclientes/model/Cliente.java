package br.net.consutarclientes.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name="nome")
    private String nome;
    @Column(name="email")
    private String email;
    @Column(name="cpf")
    private String CPF;
    @Column(name="telefone")
    private String telefone;
    @Column(name="salario")
    private float salario;
    @Column(name="rua")
    private String rua;
    @Column(name="logradouro")
    private String logradouro;
    @Column(name="numero")
    private String numero;
    @Column(name="complemento")
    private String complemento;
    @Column(name="CEP")
    private String CEP;
    @Column(name="cidade")
    private String cidade;
    @Column(name="estado")
    private String estado;

    public Cliente(){
        super();
    }

    public Cliente(Long id, String nome, String email, String cpf, String telefone, float salario, String rua, String logradouro, String numero, String complemento, String CEP, String cidade, String estado){
        super();
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.CPF = cpf;
        this.telefone = telefone;
        this.salario = salario;
        this.rua = rua;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.CEP = CEP;
        this.cidade = cidade;
        this.estado = estado;
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
        return CPF;
    }

    public void setCPF(String cpf){
        this.CPF = cpf;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public float getSalario(){
        return salario;
    }

    public void setSalario(float salario){
        this.salario = salario;
    }

    public String getRua(){
        return rua;
    }

    public void setRua(String rua){
        this.rua = rua;
    }

    public String getLogradouro(){
        return logradouro;
    }

    public void setLogradouro(String logradouro){
        this.logradouro = logradouro;
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getComplemento(){
        return complemento;
    }

    public void setComplemento(String complemento){
        this.complemento = complemento;
    }

    public String getCEP(){
        return CEP;
    }

    public void setCEP(String CEP){
        this.CEP = CEP;
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

}