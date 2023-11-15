package com.example.contasservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "movimentacao", schema = "write")
public class Movimentacao implements Serializable {
    public enum TipoMovimentacao {
        TRANSFERENCIA,
        SAQUE,
        DEPOSITO;
    }

    public enum DirecaoMovimentacao {
        ENTRADA,
        SAIDA;
    }
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "id_sequence_movimentacao")
    @SequenceGenerator(name = "id_sequence_movimentacao", sequenceName = "movimentacao_id_sequence", allocationSize = 1, schema = "write")
    @Column(name="id")
    private Long id;
    @Column(name="tipo")
    private TipoMovimentacao tipo;
    @Column(name="data")
    private Date data;
    @ManyToOne()
    @JoinColumn(name = "id_conta_origem", nullable = false)
    private Conta contaOrigem;
    @ManyToOne()
    @JoinColumn(name = "id_conta_destino")
    private Conta contaDestino;
    @ManyToOne()
    @JoinColumn(name = "cliente")
    private Cliente cliente;
    @Column(name="valor")
    private Float valor;
    @Column(name="direcao")
    private DirecaoMovimentacao direcao;

    public Movimentacao() {
    }

    public Movimentacao(Long id, TipoMovimentacao tipo, Date data, Conta contaOrigem, Conta contaDestino, Cliente cliente, Float valor, DirecaoMovimentacao direcao) {
        this.id = id;
        this.tipo = tipo;
        this.data = data;
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.cliente = cliente;
        this.valor = valor;
        this.direcao = direcao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(Conta contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(Conta contaDestino) {
        this.contaDestino = contaDestino;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public DirecaoMovimentacao getDirecao() {
        return direcao;
    }

    public void setDirecao(DirecaoMovimentacao direcao) {
        this.direcao = direcao;
    }
}
