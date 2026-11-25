package com.carlosribeiro.projeto.util;

import com.carlosribeiro.projeto.model.Produto;

import java.util.Comparator;

public class ComparadorDeProdutosPorNome implements Comparator<Produto> {

    @Override
    public int compare(Produto p1, Produto p2) {
        return p1.getNome().compareTo(p2.getNome());
    }
}
