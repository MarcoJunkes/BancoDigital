package com.example.contasservice.event;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class NovaContaEvent implements Serializable {
    private String cpf;
    private String nome;
    private Float salario;

    public NovaContaEvent() {
    }

    public NovaContaEvent(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            NovaContaEvent novaContaEvent = objectMapper.readValue(jsonString, NovaContaEvent.class);
            this.cpf = novaContaEvent.getCpf();
            this.nome = novaContaEvent.getNome();
            this.salario = novaContaEvent.getSalario();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }
}
