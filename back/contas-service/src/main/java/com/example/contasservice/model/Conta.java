package com.example.contasservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="conta", schema = "write")
public class Conta implements Serializable {
    public enum StatusConta {
        PENDENTE_APROVACAO,
        ATIVA,
        REJEITADA,
        BLOQUEADA,
        CANCELADA;
    }

    @Id
    @GeneratedValue(generator = "id_sequence_conta")
    @SequenceGenerator(name = "id_sequence_conta", sequenceName = "conta_id_sequence", allocationSize = 1, schema = "write")
    @Column(name="numero")
    private Long numero;
    @Column(name="status")
    private StatusConta status;
    @Column(name="limite")
    private Float limite;
    @Column(name="saldo")
    private Float saldo;
    @CreationTimestamp
    @Column(name="data_criacao")
    private Date dataCriacao;
    @ManyToOne
    @JoinColumn(name="id_gerente")
    private Gerente gerente;
    @OneToOne
    @JoinColumn(name="id_cliente")
    private Cliente cliente;

    public Conta() {}

    public Conta(Long numero, StatusConta status, Float limite, Float saldo, Date dataCriacao, Gerente gerente, Cliente cliente) {
        this.numero = numero;
        this.status = status;
        this.limite = limite;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.gerente = gerente;
        this.cliente = cliente;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public StatusConta getStatus() {
        return status;
    }

    public void setStatus(StatusConta status) {
        this.status = status;
    }

    public Float getLimite() {
        return limite;
    }

    public void setLimite(Float limite) {
        this.limite = limite;
    }

    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Gerente getGerente() {
        return gerente;
    }

    public void setGerente(Gerente gerente) {
        this.gerente = gerente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
