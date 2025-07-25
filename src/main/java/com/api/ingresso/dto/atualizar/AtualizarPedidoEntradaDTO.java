/**
 * 
 */
package com.api.ingresso.dto.atualizar;

import java.util.List;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.dto.ItemEntradaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record AtualizarPedidoEntradaDTO(
    @NotNull UUID usuarioId,
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull UUID sessaoId,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens
) {}