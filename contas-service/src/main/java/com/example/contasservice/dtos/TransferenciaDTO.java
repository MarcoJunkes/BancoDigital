package com.example.contasservice.dtos;

public class TransferenciaDTO {
    private Long contaDestino;
    private Float valor;

    public Long getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Long contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
