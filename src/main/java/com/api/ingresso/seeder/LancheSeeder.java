package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Lanche;
import com.api.ingresso.factory.LancheFactory;

@Component
public class LancheSeeder {

    private final LancheFactory lancheFactory;

    public LancheSeeder(LancheFactory lancheFactory) {
        this.lancheFactory = lancheFactory;
    }

    public void run() {
        System.out.println("✅ Lanches disponíveis: ");
        for (Lanche l : lancheFactory.listarTodos()) {
            System.out.println(" - " + l);
        }
    }
}
