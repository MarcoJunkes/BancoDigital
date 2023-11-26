package com.example.contasservice.dto;

import java.util.Date;

public class ContaResponseDTO {
    private Long numero;
    private Float saldo;
    private Float limite;
    private String gerenteCpf;
    private String gerenteNome;
    private String clienteCpf;
    private Date dataCriacao;

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Float getLimite() {
        return limite;
    }

    public void setLimite(Float limite) {
        this.limite = limite;
    }

    public String getGerenteCpf() {
        return gerenteCpf;
    }

    public void setGerenteCpf(String gerenteCpf) {
        this.gerenteCpf = gerenteCpf;
    }

    public String getGerenteNome() {
        return gerenteNome;
    }

    public void setGerenteNome(String gerenteNome) {
        this.gerenteNome = gerenteNome;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
