package com.example.remover_gerente.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemocaoGerenteEvent implements Serializable{
    private String cpf;
    
    public RemocaoGerenteEvent() {
    }
    public RemocaoGerenteEvent(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RemocaoGerenteEvent novaContaEvent = objectMapper.readValue(jsonString, RemocaoGerenteEvent.class);
            this.cpf = novaContaEvent.getCpf();
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
}
