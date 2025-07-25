/**
 * 
 */
package com.api.ingresso.dto.atualizar;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

/**
 * 
 */
public record AtualizarPedidoRespostaDTO(
    UUID id,
    BigDecimal valor,
    MetodoPagamento metodoPagamento
) {}