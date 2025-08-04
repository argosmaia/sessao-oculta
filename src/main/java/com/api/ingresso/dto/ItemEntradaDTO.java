package com.api.ingresso.dto;
import java.util.UUID;

import com.api.ingresso.domain.entities.Item;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Produto;

import jakarta.validation.constraints.NotNull;

/**
 *  private UUID id;
    private Produto produto;
    private Pedido pedido;
    private int quantidade;
 */
public record ItemEntradaDTO(
    @NotNull UUID id,
    @NotNull UUID produto,
    @NotNull UUID pedido,
    @NotNull int quantidade
) {
    public ItemEntradaDTO(Item itens) {
        this(
            itens.getId(),
            itens.getProduto().getId(),
            itens.getPedido().getId(),
            itens.getQuantidade()
        );
    }
}
