package com.carlosribeiro.projeto.service;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.dao.ClienteDao;
import com.carlosribeiro.projeto.exception.ClienteComHospedagensException;
import com.carlosribeiro.projeto.exception.ClienteJaCadastradoException;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Cliente;

import java.util.List;

public class ClienteService {

    @Autowired
    private ClienteDao clienteDao;

    public Cliente cadastrar(Cliente cliente) {
        if (clienteDao.recuperarPorCpf(cliente.getCpf()).isPresent()) {
            throw new ClienteJaCadastradoException(
                "Cliente com CPF = " + cliente.getCpf() + " já cadastrado.");
        }
        return clienteDao.cadastrar(cliente);
    }

    public Cliente remover(Integer id) {
        Cliente cliente = recuperar(id);
        if(cliente.getHospedagens().isEmpty()){
            return clienteDao.remover(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com id = " + id + " não encontrado."));
        } else{
            throw new ClienteComHospedagensException("Este cliente possui hospedagens, logo não pode ser removido.");
        }
    }

    public Cliente recuperar(Integer id) {
        return clienteDao.recuperar(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com id = " + id + " não encontrado."));
    }

    public Cliente recuperarPorCpf(String cpf) {
        return clienteDao.recuperarPorCpf(cpf).orElseThrow(() -> new EntidadeNaoEncontradaException("Cliente com CPF = " + cpf + " não encontrado."));
    }

    public List<Cliente> recuperarTodos() {
        return clienteDao.recuperarTodos();
    }

    public boolean vazio() {
        return clienteDao.vazio();
    }
}
