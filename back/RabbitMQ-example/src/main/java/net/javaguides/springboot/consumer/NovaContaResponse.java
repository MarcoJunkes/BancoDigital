package net.javaguides.springboot.consumer;

public class NovaContaResponse {
    private Long numero;

    public NovaContaResponse(Long numero) {
        this.numero = numero;
    }

    public NovaContaResponse() {
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }
}