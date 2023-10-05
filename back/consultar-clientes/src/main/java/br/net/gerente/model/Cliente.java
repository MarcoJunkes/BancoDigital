package br.net.gerente.model;

import java.io.Serializable;

public class Cliente implements Serializable{
	private int id;
	private String nome;
	private String cpf;
	private String cidade;
	private String estado;
	private float saldo;
	
	public Cliente() {
		super();
	}
	public Cliente(int id, String nome, String cpf, String cidade, String estado, float saldo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cidade = cidade;
		this.estado = estado;
		this.saldo = saldo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
}
