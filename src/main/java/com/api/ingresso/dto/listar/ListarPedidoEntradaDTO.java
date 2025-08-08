package com.api.ingresso.dto.listar;

import java.util.UUID;
import java.math.BigDecimal;

import com.api.ingresso.domain.entities.Pedido;

import jakarta.validation.constraints.NotNull;

public record ListarPedidoEntradaDTO(
    @NotNull Pedido pedido
) {
    
}
