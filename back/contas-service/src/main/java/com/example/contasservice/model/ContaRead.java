package com.example.contasservice.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "conta", schema = "read")
public class ContaRead implements Serializable {
    public enum StatusConta {
        PENDENTE_APROVACAO,
        ATIVA,
        REJEITADA,
        BLOQUEADA,
        CANCELADA;
    }

    @Id
    @GeneratedValue(generator = "id_sequence_conta")
    @SequenceGenerator(name = "id_sequence_conta", sequenceName = "conta_id_sequence", allocationSize = 1, schema = "read")
    @Column(name="numero")
    private Long numero;
    @Column(name="status")
    private ContaRead.StatusConta status;
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
}
