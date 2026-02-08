package com.api.ingresso.dto.listar;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import com.api.ingresso.domain.entities.Produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListarProdutoDTO(
    @NotNull UUID id,
    @NotBlank String nome,
    @NotBlank String descricao,
    @NotBlank Lanche lanche,
    @NotBlank Bebida bebida,
    @NotBlank BigDecimal preco
) {
    public ListarProdutoDTO(Produto produto) {
        this(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getLanche(),
            produto.getBebida(),
            produto.getPreco()
        );
    }
}
