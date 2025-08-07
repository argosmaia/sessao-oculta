package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Ingresso;
import com.api.ingresso.domain.entities.Pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull Ingresso ingresso,
    @NotNull BigDecimal valor,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemRespostaDTO> itens
) {
    public PedidoRespostaDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getIngresso(),
            pedido.getValor(),
            pedido.getMetodoPagamento(),
            pedido.getItens().stream().map(ItemRespostaDTO::new).toList()
        );
    }
}