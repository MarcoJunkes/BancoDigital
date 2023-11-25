package com.example.autocadastro.model;

public class ClienteAtualizacao extends Cliente {
    private Long id;

    public ClienteAtualizacao(Long id) {
        this.id = id;
    }

    public ClienteAtualizacao(String nome, String email, String cpf, String telefone, float salario, String rua, String bairro, String numero, String complemento, String CEP, String cidade, String estado, Long id) {
        super(nome, email, cpf, telefone, salario, rua, bairro, numero, complemento, CEP, cidade, estado);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
