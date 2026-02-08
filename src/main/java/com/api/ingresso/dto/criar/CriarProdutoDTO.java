package com.api.ingresso.dto.criar;

import java.math.BigDecimal;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import jakarta.validation.constraints.NotBlank;

/**
 * 	private UUID id;
 * 	private String nome;
 * 	private String descricao;
 * 	    @Enumerated(EnumType.STRING)
 *    private Lanche lanche;
 *    @Enumerated(EnumType.STRING)
 *    private Bebida bebida;
 * 	private BigDecimal preco;
 */
public record CriarProdutoDTO(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotBlank Lanche lanche,
        @NotBlank Bebida bebida,
        @NotBlank BigDecimal preco
) {
        
}
