package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoEntradaDTO(
    @NotNull UUID id,
    @NotNull UUID usuarioId,
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull UUID sessaoId,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens,
    @NotNull BigDecimal valor
) {
    
}
