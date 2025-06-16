package com.api.ingresso.validations;

import java.util.Locale;

import com.github.javafaker.Faker;

public class NomeValido {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static String gerarNomeCompleto() {
        String nome = faker.name().firstName();
        String sobrenome = faker.name().lastName();
        return nome + " " + sobrenome;
    }

    public static String[] gerarNomeESobrenomeSeparados() {
        String nome = faker.name().firstName();
        String sobrenome = faker.name().lastName();
        return new String[]{ nome, sobrenome };
    }
}
