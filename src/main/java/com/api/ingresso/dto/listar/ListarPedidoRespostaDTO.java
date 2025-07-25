package com.api.ingresso.dto.listar;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListarPedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull BigDecimal valor,
    @NotBlank MetodoPagamento metodoPagamento
) {
    
}
