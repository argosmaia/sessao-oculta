/**
 * 
 */
package com.api.ingresso.dto.criar;

import java.util.List;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.dto.ItemEntradaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record CriarPedidoEntradaDTO(
    // @NotNull Usuario usuarioId,
    // @NotNull Filme filmeId,
    // @NotNull Sala salaId,
    // @NotNull Sessao sessaoId,
    // @NotNull MetodoPagamento metodoPagamento,
    // @NotNull @Valid List<@Valid ItemEntradaDTO> itens
    @NotNull UUID usuarioId,
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull UUID sessaoId,
    @NotNull MetodoPagamento metodoPagamento,
    @NotNull @Valid List<ItemEntradaDTO> itens
) {
}