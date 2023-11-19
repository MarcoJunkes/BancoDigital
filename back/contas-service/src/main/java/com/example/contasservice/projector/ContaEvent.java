package com.example.contasservice.projector;

import com.example.contasservice.model.Cliente;
import com.example.contasservice.model.Conta;
import com.example.contasservice.model.Gerente;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

public class ContaEvent implements Serializable {
    private Long numero;
    private Conta.StatusConta status;
    private Float limite;
    private Float saldo;
    private Date dataCriacao;
    private String gerenteCpf;
    private String gerenteNome;
    private String clienteCpf;
    private String clienteNome;

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
