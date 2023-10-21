package com.example.contasservice.dtos;

public class ListagemClienteDTO {
    private String cpf;
    private Float saldo;

    public ListagemClienteDTO() {}

    public ListagemClienteDTO(String cpf, Float saldo) {
        this.cpf = cpf;
        this.saldo = saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }
}
