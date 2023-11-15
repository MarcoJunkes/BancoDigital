package com.example.contasservice.projector;

import com.example.contasservice.model.Movimentacao;

import java.io.Serializable;

public class MovimentacaoEvent implements Serializable {
    private Movimentacao movimentacao;

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }
}
