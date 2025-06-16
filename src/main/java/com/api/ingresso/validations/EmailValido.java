package com.api.ingresso.validations;

import java.util.Random;

public class EmailValido {

    private static final String[] DOMINIOS = {
        "@gmail.com", "@outlook.com", "@hotmail.com", "@yahoo.com.br", "@proton.me"
    };

    private static final Random random = new Random();

    public static String gerarEmail(String nome, String sobrenome) {
        nome = nome.toLowerCase().replaceAll("[^a-z]", "");
        sobrenome = sobrenome.toLowerCase().replaceAll("[^a-z]", "");

        String[] combinacoes = {
            nome + "." + sobrenome,
            sobrenome + "." + nome,
            nome + sobrenome,
            sobrenome + nome
        };

        String combinacao = combinacoes[random.nextInt(combinacoes.length)];
        String dominio = DOMINIOS[random.nextInt(DOMINIOS.length)];

        return combinacao + dominio;
    }
}
