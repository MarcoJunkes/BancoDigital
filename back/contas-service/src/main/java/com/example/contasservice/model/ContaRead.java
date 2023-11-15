package com.example.contasservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "conta", schema = "read")
public class ContaRead implements Serializable {
    @Id
    @GeneratedValue(generator = "id_sequence_conta")
    @SequenceGenerator(name = "id_sequence_conta", sequenceName = "conta_id_sequence", allocationSize = 1, schema = "read")
    @Column(name="numero")
    private Long numero;
    @Column(name="status")
    private Conta.StatusConta status;
    @Column(name="limite")
    private Float limite;
    @Column(name="saldo")
    private Float saldo;
    @CreationTimestamp
    @Column(name="data_criacao")
    private Date dataCriacao;
    @Column(name="gerente_cpf")
    private String gerenteCpf;
    @Column(name="gerente_nome")
    private String gerenteNome;
    @Column(name="cliente_cpf")
    private String clienteCpf;
    @Column(name="cliente_nome")
    private String clienteNome;

//    @Column(name = "movimentacoes", columnDefinition = "jsonb")
//    @Type(JsonType.class)
//    private List<JsonNode> movimentacoes;


    public ContaRead() {
    }

    public ContaRead(Long numero, Conta.StatusConta status, Float limite, Float saldo, Date dataCriacao, String gerenteCpf, String gerenteNome, String clienteCpf, String clienteNome) {
        this.numero = numero;
        this.status = status;
        this.limite = limite;
        this.saldo = saldo;
        this.dataCriacao = dataCriacao;
        this.gerenteCpf = gerenteCpf;
        this.gerenteNome = gerenteNome;
        this.clienteCpf = clienteCpf;
        this.clienteNome = clienteNome;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public Conta.StatusConta getStatus() {
        return status;
    }

    public void setStatus(Conta.StatusConta status) {
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

    public String getGerenteCpf() {
        return gerenteCpf;
    }

    public void setGerenteCpf(String gerenteCpf) {
        this.gerenteCpf = gerenteCpf;
    }

    public String getGerenteNome() {
        return gerenteNome;
    }

    public void setGerenteNome(String gerenteNome) {
        this.gerenteNome = gerenteNome;
    }

    public String getClienteCpf() {
        return clienteCpf;
    }

    public void setClienteCpf(String clienteCpf) {
        this.clienteCpf = clienteCpf;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }
}
