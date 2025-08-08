package com.api.ingresso.factory;

import java.time.LocalDate;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.api.ingresso.dto.EnderecoDTO;
import com.api.ingresso.dto.criar.CriarUsuarioDTO;
import com.api.ingresso.validations.CPFValido;
import com.api.ingresso.validations.EmailValido;
import com.api.ingresso.validations.NomeValido;
import com.api.ingresso.validations.TelefoneValido;
import com.github.javafaker.Faker;

@Component
public class UsuarioFactory {

    private final Faker faker = new Faker(new Locale("pt-BR"));

    public CriarUsuarioDTO criarUsuarioDTOAleatorio() {
        String[] nomeSeparado = NomeValido.gerarNomeESobrenomeSeparados();
        String nomeCompleto = nomeSeparado[0] + " " + nomeSeparado[1];
        String email = EmailValido.gerarEmail(nomeSeparado[0], nomeSeparado[1]);
        
        return new CriarUsuarioDTO(
            nomeCompleto,
            LocalDate.of(1990 + faker.random().nextInt(0, 20), faker.random().nextInt(1, 12), faker.random().nextInt(1, 28)),
            CPFValido.gerar(),
            email,
            faker.internet().password(8, 16),
            TelefoneValido.gerarTelefone(),
            new EnderecoDTO(
                faker.address().streetAddress(),
                faker.address().cityName(),
                faker.number().digits(8),
                faker.address().city(),
                faker.address().stateAbbr(),
                faker.address().secondaryAddress(),
                faker.address().buildingNumber()
            )
        );
    }
}
