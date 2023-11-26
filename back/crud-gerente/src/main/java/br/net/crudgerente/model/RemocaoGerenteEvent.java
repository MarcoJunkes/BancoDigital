package br.net.crudgerente.model;

import java.io.Serializable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RemocaoGerenteEvent implements Serializable{
    private int id;
    
    public RemocaoGerenteEvent() {
    }
    public RemocaoGerenteEvent(String jsonString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            RemocaoGerenteEvent novaContaEvent = objectMapper.readValue(jsonString, RemocaoGerenteEvent.class);
            this.id = novaContaEvent.getId();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}