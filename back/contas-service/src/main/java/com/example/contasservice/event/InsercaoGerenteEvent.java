package com.example.contasservice.event;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class InsercaoGerenteEvent implements Serializable {
    private String cpf;
    private String nome;

    public InsercaoGerenteEvent() {
    }
    public InsercaoGerenteEvent(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InsercaoGerenteEvent gerenteEvent = objectMapper.readValue(jsonString, InsercaoGerenteEvent.class);
            this.cpf = gerenteEvent.getCpf();
            this.nome = gerenteEvent.getNome();
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
}