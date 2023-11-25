package com.example.contasservice.dto;

public class TransferenciaRequestDTO {
    private Long destino;
    private Float valor;

    public Long getDestino() {
        return destino;
    }

    public void setDestino(Long destino) {
        this.destino = destino;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }
}
