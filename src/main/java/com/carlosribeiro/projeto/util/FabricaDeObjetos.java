package com.carlosribeiro.projeto.util;

import com.carlosribeiro.projeto.annotation.Autowired;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FabricaDeObjetos {
    private final static Map<Class<?>, Object> map = new HashMap<>();

    public static <T> T getInstance(Class<T> tipo) throws RuntimeException {
        Object obj = map.get(tipo);
        if (obj == null) {
            try {
                if (tipo.isInterface()) {
                    Reflections reflections = new Reflections("com.carlosribeiro.projeto.dao.impl");
                    Set<Class<? extends T>> conjunto = reflections.getSubTypesOf(tipo);
                    if (conjunto.size() != 1)
                        throw new RuntimeException(
                                "Deve haver uma e apenas uma classe que implementa a interface " +
                                        tipo.getName());
                    Class<? extends T> classe = conjunto.iterator().next();
                    obj = classe.getConstructor().newInstance();
                }
                else {
                    obj = tipo.getConstructor().newInstance();
                }
                map.put(tipo, obj);

                for (Field campo : tipo.getDeclaredFields()){
                    if(campo.isAnnotationPresent(Autowired.class)){
                        campo.setAccessible(true);
                        campo.set(obj, FabricaDeObjetos.getInstance(campo.getType()));
                    }
                }
            } catch (InstantiationException |
                     IllegalAccessException |
                     InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
        return tipo.cast(obj);
    }
}
