package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.factory.BebidaFactory;

@Component
public class BebidaSeeder {

    private final BebidaFactory bebidaFactory;

    public BebidaSeeder(BebidaFactory bebidaFactory) {
        this.bebidaFactory = bebidaFactory;
    }

    public void run() {
        System.out.println("✅ Bebidas disponíveis: ");
        for (Bebida b : bebidaFactory.listarTodos()) {
            System.out.println(" - " + b);
        }
    }
}
