package br.net.crudgerente.model;

import java.io.Serializable;

public class RemocaoGerenteEvent implements Serializable {
    private String cpf;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
