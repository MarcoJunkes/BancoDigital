package com.example.contasservice.projector;

import com.example.contasservice.model.Conta;

import java.io.Serializable;

public class ContaEvent implements Serializable {
    private Conta conta;

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
