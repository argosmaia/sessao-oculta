/**
 * 
 */
package com.api.ingresso.dto.criar;

import java.math.BigDecimal;
import java.util.UUID;
import com.api.ingresso.domain.embeddable.MetodoPagamento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record CriarPedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull BigDecimal valor,
    @NotBlank MetodoPagamento metodoPagamento
) {}