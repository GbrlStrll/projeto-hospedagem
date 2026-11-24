package com.carlosribeiro.projeto.dao.impl;

import com.carlosribeiro.projeto.dao.ClienteDao;
import com.carlosribeiro.projeto.model.Cliente;

import java.util.Optional;

public class ClienteDaoImpl extends DaoGenericoImpl<Cliente> implements ClienteDao {

    @Override
    public Optional<Cliente> recuperarPorCpf(String cpf) {
        return map.values()
                  .stream()
                  .filter(cliente -> cliente.getCpf().equals(cpf))
                  .findFirst();
    }
}
