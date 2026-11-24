package com.carlosribeiro.projeto.model;

import com.carlosribeiro.projeto.annotation.Id;
import com.carlosribeiro.projeto.util.Util;

import java.io.Serializable;

public class Produto implements Serializable {
	@Id
	private int numero;
	private String nome;
	private double preco;

	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String toString() {
		return "Número = " + numero +
			   "  Nome = " + nome +
			   "  Preço = " + Util.formatarMoeda(preco);
	}

	public int getNumero() {
		return numero;
	}
	public String getNome()	{
		return nome;
	}
	public double getPreco() {
		return preco;
	}
	private void setNumero(int numero) {
		this.numero = numero;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
}