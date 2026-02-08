package com.api.ingresso.factory;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Lanche;

@Component
public class LancheFactory {

    private final List<Lanche> lanches = Arrays.asList(Lanche.values());

    public Lanche criarLancheAleatorio() {
        return lanches.get(ThreadLocalRandom.current().nextInt(lanches.size()));
    }

    public List<Lanche> listarTodos() {
        return lanches;
    }
}

