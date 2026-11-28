package com.carlosribeiro.projeto.model;

import com.carlosribeiro.projeto.annotation.Id;
import com.carlosribeiro.projeto.util.Util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Hospedagem implements Serializable {
    @Id
    private int id;
    private LocalDateTime dataHoraEntradaPrevista;
    private LocalDateTime dataHoraEntradaReal;
    private LocalDateTime dataHoraSaidaPrevista;
    private LocalDateTime dataHoraSaidaReal;
    private double precoDaDiaria;
    private Cliente cliente;
    private Acomodacao acomodacao;
    private List<Consumo> consumos;

    public Hospedagem(LocalDateTime dataHoraEntradaPrevista, LocalDateTime dataHoraSaidaPrevista, Cliente cliente, Acomodacao acomodacao) {
        this.dataHoraEntradaPrevista = dataHoraEntradaPrevista;
        this.dataHoraSaidaPrevista = dataHoraSaidaPrevista;
        this.cliente = cliente;
        this.acomodacao = acomodacao;
        this.consumos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDataHoraEntradaPrevista() {
        return dataHoraEntradaPrevista;
    }

    public void setDataHoraEntradaPrevista(LocalDateTime dataHoraEntradaPrevista) {
        this.dataHoraEntradaPrevista = dataHoraEntradaPrevista;
    }

    public LocalDateTime getDataHoraEntradaReal() {
        return dataHoraEntradaReal;
    }

    public void setDataHoraEntradaReal(LocalDateTime dataHoraEntradaReal) {
        this.dataHoraEntradaReal = dataHoraEntradaReal;
    }

    public LocalDateTime getDataHoraSaidaPrevista() {
        return dataHoraSaidaPrevista;
    }

    public void setDataHoraSaidaPrevista(LocalDateTime dataHoraSaidaPrevista) {
        this.dataHoraSaidaPrevista = dataHoraSaidaPrevista;
    }

    public LocalDateTime getDataHoraSaidaReal() {
        return dataHoraSaidaReal;
    }

    public void setDataHoraSaidaReal(LocalDateTime dataHoraSaidaReal) {
        this.dataHoraSaidaReal = dataHoraSaidaReal;
    }

    public double getPrecoDaDiaria() {
        return precoDaDiaria;
    }

    public void setPrecoDaDiaria(double precoDaDiaria) {
        this.precoDaDiaria = precoDaDiaria;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Acomodacao getAcomodacao() {
        return acomodacao;
    }

    public void setAcomodacao(Acomodacao acomodacao) {
        this.acomodacao = acomodacao;
    }

    public List<Consumo> getConsumos() {
        return consumos;
    }

    public void setConsumos(List<Consumo> consumos) {
        this.consumos = consumos;
    }

    public String toString() {
        return "Id = " + id +
        "  Cliente = " + cliente.getNome() +
        "  Quarto = " + acomodacao.getNumeroDoQuarto() +
        "  Preço da diária = " + Util.formatarMoeda(precoDaDiaria) +
        "  Entrada prevista = " + Util.formatarDataHora(dataHoraEntradaPrevista) +
        "  Entrada real = " + Util.formatarDataHora(dataHoraEntradaReal) +
        "  Saída prevista = " + Util.formatarDataHora(dataHoraSaidaPrevista) +
        "  Saída real = " + Util.formatarDataHora(dataHoraSaidaReal);
    }

}
