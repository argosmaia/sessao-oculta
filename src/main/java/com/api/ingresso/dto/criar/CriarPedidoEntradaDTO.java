/**
 * 
 */
package com.api.ingresso.dto.criar;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.dto.ItemEntradaDTO;

import jakarta.validation.Valid;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record CriarPedidoEntradaDTO(
    @NotNull UUID usuarioId,
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull UUID sessaoId,
    @NotNull MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens
) {}