package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Ingresso;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoEntradaDTO(
    @NotNull UUID id,
    @NotNull Ingresso ingresso,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens,
    @NotNull BigDecimal valor
) {
    public PedidoEntradaDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getIngresso(),
            pedido.getMetodoPagamento(),
            pedido.getItens().stream().map(ItemEntradaDTO::new).toList(),
            pedido.getValor()
        );
    }
}
