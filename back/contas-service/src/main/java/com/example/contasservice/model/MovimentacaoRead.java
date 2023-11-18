package com.example.contasservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movimentacao", schema = "read")
public class MovimentacaoRead implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "id_sequence_movimentacao")
    @SequenceGenerator(name = "id_sequence_movimentacao", sequenceName = "movimentacao_id_sequence", allocationSize = 1, schema = "read")
    @Column(name="id")
    private Long id;
    @Column(name="tipo")
    private Movimentacao.TipoMovimentacao tipo;
    @Column(name="data")
    private Date data;
    @ManyToOne()
    @JoinColumn(name="id_conta_origem")
    private ContaRead contaOrigem;
    @ManyToOne()
    @JoinColumn(name="id_conta_destino")
    private ContaRead contaDestino;
    @Column(name="id_cliente_origem")
    private String clienteCpf;
    @Column(name="valor")
    private Float valor;
    @Column(name="direcao")
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

    public ContaRead getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(ContaRead contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public ContaRead getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(ContaRead contaDestino) {
        this.contaDestino = contaDestino;
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
