package com.example.contasservice.dto;

public class ContasGerenteDTO {
    private String cpf;
    private String nome;
    private Long numeroClientes;
    private Double saldoPositivo;
    private Double saldoNegativo;

    public String getGerenteCpf(){
        return cpf;
    }

    public void setGerenteCpf(String cpf){
        this.cpf = cpf;
    }

    public String getGerenteNome(){
        return nome;
    }

    public void setGerenteNome(String nome){
        this.nome = nome;
    }

    public Long getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(Long numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public Double getSaldoPositivo() {
        return saldoPositivo;
    }

    public void setSaldoPositivo(Double saldoPositivo) {
        this.saldoPositivo = saldoPositivo;
    }

    public Double getSaldoNegativo() {
        return saldoNegativo;
    }

    public void setSaldoNegativo(Double saldoNegativo) {
        this.saldoNegativo = saldoNegativo;
    }
}
