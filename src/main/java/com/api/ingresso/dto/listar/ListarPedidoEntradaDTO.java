package com.api.ingresso.dto.listar;

import java.util.UUID;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;

public record ListarPedidoEntradaDTO(
    @NotNull UUID pedidoId,
    @NotNull BigDecimal valor
) {
    
}
