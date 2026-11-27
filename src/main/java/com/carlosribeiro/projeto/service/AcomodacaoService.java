package com.carlosribeiro.projeto.service;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.dao.AcomodacaoDao;
import com.carlosribeiro.projeto.exception.AcomodacaoComHospedagensException;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Acomodacao;

import java.util.List;

public class AcomodacaoService {

    @Autowired
    private AcomodacaoDao acomodacaoDao;

    public Acomodacao cadastrar(Acomodacao acomodacao) {
        return acomodacaoDao.cadastrar(acomodacao);
    }

    public Acomodacao remover(Integer id) {
        Acomodacao acomodacao = recuperar(id);
        if(acomodacao.getHospedagens().isEmpty()){
            return acomodacaoDao.remover(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Acomodação com id = " + id + " não encontrada."));
        } else{
            throw new AcomodacaoComHospedagensException("Esta acomodação possui hospedagens, logo não pode ser removida.");
        }
    }

    public Acomodacao recuperar(Integer id) {
        return acomodacaoDao.recuperar(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Acomodação com id = " + id + " não encontrada."));
    }

    public List<Acomodacao> recuperarTodos() {
        return acomodacaoDao.recuperarTodos();
    }

    public boolean vazio() {
        return acomodacaoDao.vazio();
    }
}
