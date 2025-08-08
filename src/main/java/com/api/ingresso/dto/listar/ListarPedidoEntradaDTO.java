package com.api.ingresso.dto.listar;

import com.api.ingresso.domain.entities.Pedido;

import jakarta.validation.constraints.NotNull;

public record ListarPedidoEntradaDTO(
    @NotNull Pedido pedido
) {
    
}
