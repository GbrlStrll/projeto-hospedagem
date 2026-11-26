package com.carlosribeiro.projeto.model;

import com.carlosribeiro.projeto.annotation.Id;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
	@Id
	private int id;
	private String nome;
	private String cpf;
	private String endereco;
	private List<Hospedagem> hospedagens;

	public Cliente(String nome, String cpf, String endereco) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.hospedagens = new ArrayList<>();
	}

	public String toString() {
		return "Id = " + id +
			   "  Nome = " + nome +
			   "  CPF = " + cpf +
			   "  Endereço = " + endereco;
	}

	public int getId() {
		return id;
	}
	public String getNome()	{
		return nome;
	}
	public String getCpf() {
		return cpf;
	}
	public String getEndereco() {
		return endereco;
	}
	public List<Hospedagem> getHospedagens() {
		return hospedagens;
	}

	private void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public void setHospedagens(List<Hospedagem> hospedagens) {
		this.hospedagens = hospedagens;
	}
}