package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.entities.Item;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRespostaDTO(
    @NotNull UUID id,
    @NotNull UUID pedido,
    @NotBlank String nomeProduto,
    @NotNull int quantidade
) {
    public ItemRespostaDTO(Item itens) {
        this(
            itens.getId(),
            itens.getPedido().getId(),
            itens.getProduto().getNome(),
            itens.getQuantidade()
        );
    }
}