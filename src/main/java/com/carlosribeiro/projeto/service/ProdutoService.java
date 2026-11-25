package com.carlosribeiro.projeto.service;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.dao.ProdutoDao;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Produto;

import java.util.List;

public class ProdutoService {

    @Autowired
    private ProdutoDao produtoDao;

    public Produto cadastrar(Produto produto) {
        return produtoDao.cadastrar(produto);
    }

    public Produto remover(Integer numero) {
        return produtoDao.remover(numero).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com número = " + numero + " não encontrado."));
    }

    public Produto recuperar(Integer numero) {
        return produtoDao.recuperar(numero).orElseThrow(() -> new EntidadeNaoEncontradaException("Produto com número = " + numero + " não encontrado."));
    }

    public List<Produto> recuperarTodos() {
        return produtoDao.recuperarTodos();
    }

    public boolean vazio() {
        return produtoDao.vazio();
    }

    public List<Produto> recuperarProdutosOrdenadosPorNome() {
        return produtoDao.recuperarProdutosOrdenadosPorNome();
    }

    public List<Produto> recuperarProdutosOrdenadosDescendentementePorNome() {
        return produtoDao.recuperarProdutosOrdenadosDescendentementePorNome();
    }
}
