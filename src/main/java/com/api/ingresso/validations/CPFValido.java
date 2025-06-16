package com.api.ingresso.validations;

import java.util.Random;

public class CPFValido {

    public static String gerar() {
        Random random = new Random();

        int[] cpf = new int[11];

        // Gera os 9 primeiros dígitos
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcula o primeiro dígito verificador
        cpf[9] = calcularDigito(cpf, 10);

        // Calcula o segundo dígito verificador
        cpf[10] = calcularDigito(cpf, 11);

        // Formata para string com máscara opcional
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cpf.length; i++) {
            sb.append(cpf[i]);
        }

        return sb.toString();
    }

    private static int calcularDigito(int[] cpf, int pesoInicial) {
        int soma = 0;
        for (int i = 0; i < pesoInicial - 1; i++) {
            soma += cpf[i] * (pesoInicial - i);
        }

        int resto = soma % 11;
        return (resto < 2) ? 0 : 11 - resto;
    }
}