package com.example.autocadastro.model;

public class RejeitarCliente {
    private String motivo;

    public RejeitarCliente(){

    }

    public RejeitarCliente(String motivo){
        this.motivo = motivo;
    }

    public String getMotivo(){
        return motivo;
    }

    public void setMotivo(String motivo){
        this.motivo = motivo;
    }
}
