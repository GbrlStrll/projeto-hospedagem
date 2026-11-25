package com.carlosribeiro.projeto.dao;

import com.carlosribeiro.projeto.model.Produto;

import java.util.List;

public interface ProdutoDao extends DaoGenerico<Produto> {
    List<Produto> recuperarProdutosOrdenadosPorNome();
    List<Produto> recuperarProdutosOrdenadosDescendentementePorNome();
}
