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
    private String tipo;
    @Column(name="data")
    private Date data;
    @Column(name="id_cliente_origem")
    private String clienteOrigemCpf;
    @Column(name="id_cliente_destino")
    private String clienteDestinoCpf;
    @Column(name="valor")
    private Float valor;
    @Column(name="direcao")
    private String direcao;
}
