package com.example.contasservice.dto;

public class ContasGerenteDTO {
    private Long numeroClientes;
    private Double saldoPositivo;
    private Double saldoNegativo;

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
