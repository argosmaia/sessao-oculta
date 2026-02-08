package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.factory.SalaFactory;
import com.api.ingresso.repository.SalaRepository;

@Component
public class SalaSeeder {

    private final SalaRepository salaRepository;
    private final SalaFactory salaFactory;

    public SalaSeeder(SalaRepository salaRepository, SalaFactory salaFactory) {
        this.salaRepository = salaRepository;
        this.salaFactory = salaFactory;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            var dto = salaFactory.criarSalaDTOAleatoria();
            salaRepository.save(new Sala(dto)); // precisa do construtor Sala(CriarSalaDTO)
        }
        System.out.println("✅ 10 salas foram inseridas no banco.");
    }
}
