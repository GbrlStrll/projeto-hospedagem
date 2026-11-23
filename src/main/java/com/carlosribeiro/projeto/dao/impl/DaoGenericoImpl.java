package com.carlosribeiro.projeto.dao.impl;

import com.carlosribeiro.projeto.annotation.Id;
import com.carlosribeiro.projeto.dao.DaoGenerico;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DaoGenericoImpl<V> implements DaoGenerico<V> {

    protected Map<Integer, V> map = new LinkedHashMap<>();
    private int contador = 0;

    @Override
    public Map<Integer, V> getMap() {
        return map;
    }

    @Override
    public void setMap(Map<Integer, V> map) {
        this.map = map;
    }

    @Override
    public int getContador() {
        return contador;
    }

    @Override
    public void setContador(int contador) {
        this.contador = contador;
    }

    @Override
    public V cadastrar(V obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field campo : fields) {
            if (campo.isAnnotationPresent(Id.class)) {
                String metodoSet = "set" + campo.getName().substring(0,1).toUpperCase() +
                    campo.getName().substring(1);
                try {
                    Method methodSet = obj.getClass().getDeclaredMethod(metodoSet, int.class);
                    methodSet.setAccessible(true);
                    methodSet.invoke(obj, ++contador);
                    map.put(contador, obj);
                    return obj;
                } catch (NoSuchMethodException |
                         InvocationTargetException |
                         IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Não achou método anotado com @Id");
    }

    @Override
    public Optional<V> remover(Integer numero) {
        return Optional.ofNullable(map.remove(numero));
    }

    @Override
    public Optional<V> recuperar(Integer numero) {
        return Optional.ofNullable(map.get(numero));
    }

    @Override
    public List<V> recuperarTodos() {
        return map.values().stream().toList();
    }

    @Override
    public boolean vazio() {
        return map.isEmpty();
    }
}
