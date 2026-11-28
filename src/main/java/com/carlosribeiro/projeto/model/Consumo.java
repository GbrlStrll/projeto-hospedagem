package com.carlosribeiro.projeto.model;

import com.carlosribeiro.projeto.annotation.Id;
import com.carlosribeiro.projeto.util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Consumo implements Serializable {
    @Id
    private int id;
    private int quantidade;
    private LocalDateTime dataHora;
    private double preco;
    private Hospedagem hospedagem;
    private Produto produto;

    public Consumo(int quantidade, LocalDateTime dataHora, Hospedagem hospedagem, Produto produto) {
        this.quantidade = quantidade;
        this.dataHora = dataHora;
        this.hospedagem = hospedagem;
        this.produto = produto;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public Hospedagem getHospedagem() {
        return hospedagem;
    }

    public void setHospedagem(Hospedagem hospedagem) {
        this.hospedagem = hospedagem;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String toString() {
        return "Id = " + id +
        "  Quantidade = " + quantidade +
        "  Data e hora = " + Util.formatarDataHora(dataHora) +
        "  Preço = " + Util.formatarMoeda(preco) +
        "  Hospedagem = " + hospedagem.getId() +
        "  Produto = " + produto.toString();
    }

}
