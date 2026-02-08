package com.api.ingresso.dto.atualizar;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarProdutoDTO(
    @NotNull UUID id,
    @NotBlank String nome,
    @NotBlank String descricao,
    @NotBlank Lanche lanche,
    @NotBlank Bebida bebida,
    @NotBlank BigDecimal preco
) {
    
}
