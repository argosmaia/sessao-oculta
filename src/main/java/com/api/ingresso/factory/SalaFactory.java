package com.api.ingresso.factory;

import java.util.Locale;
import org.springframework.stereotype.Component;

import com.api.ingresso.dto.EnderecoDTO;
import com.api.ingresso.dto.criar.CriarSalaDTO;
import com.github.javafaker.Faker;

@Component
public class SalaFactory {

    private final Faker faker = new Faker(new Locale("pt-BR"));

    public CriarSalaDTO criarSalaDTOAleatoria() {
        String nomeSala = "Sala " + faker.number().numberBetween(1, 20); // Sala 1..20

        EnderecoDTO endereco = new EnderecoDTO(
                faker.address().streetAddress(),
                faker.address().cityName(),
                faker.number().digits(8),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().secondaryAddress(),
                faker.address().buildingNumber()
            );

        return new CriarSalaDTO(nomeSala, endereco);
    }
}
