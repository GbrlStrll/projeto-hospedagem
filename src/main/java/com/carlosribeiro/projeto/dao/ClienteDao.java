package com.carlosribeiro.projeto.dao;

import com.carlosribeiro.projeto.model.Cliente;

import java.util.Optional;

public interface ClienteDao extends DaoGenerico<Cliente>{
    Optional<Cliente> recuperarPorCpf(String cpf);
}
