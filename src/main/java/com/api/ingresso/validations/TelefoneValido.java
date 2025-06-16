package com.api.ingresso.validations;

import java.util.Random;

public class TelefoneValido {

    private static final Random random = new Random();

    // Lista dos DDDs válidos conforme especificado
    private static final int[] DDD_VALIDOS = {
        11, 12, 13, 14, 15, 16, 17, 18, 19,
        21, 22, 24,
        27, 28,
        31, 32, 33, 34, 35, 37, 38,
        41, 42, 43, 44, 45, 46, 47, 48, 49,
        51, 52, 53, 54, 55,
        61, 62, 63, 64, 65, 66, 67,
        71, 72, 73, 74, 75, 76, 77, 78, 79,
        81, 82, 83, 84, 85, 86, 87, 88, 89,
        91, 92, 93, 94, 95, 96, 97
    };

    public static String gerarTelefone() {
        int ddd = gerarDDD();
        String numero = gerarNumero();

        return "55" + ddd + numero;
    }

    private static int gerarDDD() {
        // Escolhe um DDD aleatório da lista válida
        return DDD_VALIDOS[random.nextInt(DDD_VALIDOS.length)];
    }

    private static String gerarNumero() {
        boolean celular = random.nextBoolean();

        StringBuilder numero = new StringBuilder();

        if (celular) {
            // 9 dígitos: primeiro é 9, segundo é 7, 8 ou 9
            numero.append("9");
            numero.append(7 + random.nextInt(3)); // 7, 8 ou 9
            for (int i = 0; i < 7; i++) {
                numero.append(random.nextInt(10));
            }
        } else {
            // 8 dígitos: primeiro é 7, 8 ou 9
            numero.append(7 + random.nextInt(3)); // 7, 8 ou 9
            for (int i = 0; i < 7; i++) {
                numero.append(random.nextInt(10));
            }
        }

        return numero.toString();
    }
}
