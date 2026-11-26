package com.carlosribeiro.projeto.model;

import com.carlosribeiro.projeto.annotation.Id;
import com.carlosribeiro.projeto.util.Util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Acomodacao implements Serializable {
	@Id
	private int id;
	private int numeroDoQuarto;
	private double precoDaDiaria;
	private List<Hospedagem> hospedagens;

	public Acomodacao(int numeroDoQuarto, double precoDaDiaria) {
		this.numeroDoQuarto = numeroDoQuarto;
		this.precoDaDiaria = precoDaDiaria;
		this.hospedagens = new ArrayList<>();
	}

	public String toString() {
		return "Id = " + id +
			   "  Número do quarto = " + numeroDoQuarto +
			   "  Preço da diária = " + Util.formatarMoeda(precoDaDiaria);
	}

	public int getId() {
		return id;
	}
	public int getNumeroDoQuarto() {
		return numeroDoQuarto;
	}
	public double getPrecoDaDiaria() {
		return precoDaDiaria;
	}
	public List<Hospedagem> getHospedagens() {
		return hospedagens;
	}

	private void setId(int id) {
		this.id = id;
	}
	public void setNumeroDoQuarto(int numeroDoQuarto) {
		this.numeroDoQuarto = numeroDoQuarto;
	}
	public void setPrecoDaDiaria(double precoDaDiaria) {
		this.precoDaDiaria = precoDaDiaria;
	}
	public void setHospedagens(List<Hospedagem> hospedagens) {
		this.hospedagens = hospedagens;
	}
}