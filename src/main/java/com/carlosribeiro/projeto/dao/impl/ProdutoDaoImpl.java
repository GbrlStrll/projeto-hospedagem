package com.carlosribeiro.projeto.dao.impl;

import com.carlosribeiro.projeto.dao.ProdutoDao;
import com.carlosribeiro.projeto.model.Produto;

import java.util.Comparator;
import java.util.List;

public class ProdutoDaoImpl extends DaoGenericoImpl<Produto> implements ProdutoDao {

    @Override
    public List<Produto> recuperarProdutosOrdenadosPorNome() {
        return map.values()
                  .stream()
                  .sorted(Comparator.comparing(Produto::getNome))
                  .toList();
    }

    @Override
    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        return map.values()
            .stream()
            .sorted(Comparator.comparing(Produto::getNome).reversed())
            .toList();
    }
}
