package com.example.contasservice.dtos;

public class ListagemContaResponseDTO {
    private int numeroClientes;
    private Float saldoPositivo;
    private Float saldoNegativo;

    public ListagemContaResponseDTO() {}

    public ListagemContaResponseDTO(int numeroClientes, Float saldoPositivo, Float saldoNegativo) {
        this.numeroClientes = numeroClientes;
        this.saldoPositivo = saldoPositivo;
        this.saldoNegativo = saldoNegativo;
    }

    public int getNumeroClientes() {
        return numeroClientes;
    }

    public void setNumeroClientes(int numeroClientes) {
        this.numeroClientes = numeroClientes;
    }

    public Float getSaldoPositivo() {
        return saldoPositivo;
    }

    public void setSaldoPositivo(Float saldoPositivo) {
        this.saldoPositivo = saldoPositivo;
    }

    public Float getSaldoNegativo() {
        return saldoNegativo;
    }

    public void setSaldoNegativo(Float saldoNegativo) {
        this.saldoNegativo = saldoNegativo;
    }
}
