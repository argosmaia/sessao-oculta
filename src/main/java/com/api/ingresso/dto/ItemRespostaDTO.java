package com.api.ingresso.dto;

import java.util.UUID;

import com.api.ingresso.domain.entities.Item;
import com.api.ingresso.domain.entities.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRespostaDTO(
    @NotNull UUID id,
    @NotBlank Produto nomeProduto,
    @NotNull int quantidade
) {
    public ItemRespostaDTO(Item itens) {
        this(
            itens.getId(),
            itens.getProduto(),
            itens.getQuantidade()
        );
    }
}