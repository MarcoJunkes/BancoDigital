package com.example.contasservice.projector;

import com.example.contasservice.model.ContaRead;
import com.example.contasservice.model.Movimentacao;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Date;

public class MovimentacaoEvent implements Serializable {
    private Long id;
    private Movimentacao.TipoMovimentacao tipo;
    private Date data;
    private Long contaOrigemId;
    private Long contaDestinoId;
    private String clienteCpf;
    private Float valor;
    private Movimentacao.DirecaoMovimentacao direcao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movimentacao.TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(Movimentacao.TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getContaOrigemId() {
        return contaOrigemId;
    }

    public void setContaOrigemId(Long contaOrigemId) {
        this.contaOrigemId = contaOrigemId;
    }

    public Long getContaDestinoId() {
        return contaDestinoId;
    }

    public void setContaDestinoId(Long contaDestinoId) {
        this.contaDestinoId = contaDestinoId;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public Movimentacao.DirecaoMovimentacao getDirecao() {
        return direcao;
    }

    public void setDirecao(Movimentacao.DirecaoMovimentacao direcao) {
        this.direcao = direcao;
    }
}
