package com.carlosribeiro.projeto.dao;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DaoGenerico<V> {
    Map<Integer, V> getMap();
    void setMap(Map<Integer, V> map);
    int getContador();
    void setContador(int contador);

    V cadastrar(V obj);
    Optional<V> remover(Integer numero);
    Optional<V> recuperar(Integer numero);
    List<V> recuperarTodos();
    boolean vazio();
}
