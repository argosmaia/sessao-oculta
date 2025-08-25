package com.api.ingresso.validations;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

public class PagamentoValidator {

    private PagamentoValidator() {}

    public static void validarMetodoPagamento(MetodoPagamento metodo) {
        if (metodo == null) {
            throw new IllegalArgumentException("Método de pagamento não pode ser nulo.");
        }

        switch (metodo) {
            case CREDITO, DEBITO, PIX, DINHEIRO:
                break; // válido
            default:
                throw new IllegalArgumentException("Método de pagamento inválido: " + metodo);
        }
    }
}
