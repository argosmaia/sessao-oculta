package com.api.ingresso.dto;

import java.util.UUID;

import com.api.ingresso.domain.entities.Item;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Produto;

import jakarta.validation.constraints.NotNull;

public record ItemRespostaDTO(
    @NotNull UUID id,
    @NotNull Pedido pedido,
    @NotNull int quantidade
) {
    public ItemRespostaDTO(Item itens) {
        this(
            itens.getId(),
            itens.getPedido(),
            itens.getQuantidade()
        );
    }
}