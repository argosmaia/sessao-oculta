/**
 * 
 */
package com.api.ingresso.dto.atualizar;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record AtualizarPedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull BigDecimal valor,
    @NotNull MetodoPagamento metodoPagamento
) {}