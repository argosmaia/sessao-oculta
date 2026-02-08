package com.api.ingresso.factory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Bebida;

@Component
public class BebidaFactory {

    private final List<Bebida> bebidas = Arrays.asList(Bebida.values());

    public Bebida criarBebidaAleatoria() {
        return bebidas.get(ThreadLocalRandom.current().nextInt(bebidas.size()));
    }

    public List<Bebida> listarTodos() {
        return bebidas;
    }
}
