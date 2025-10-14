package com.api.ingresso.dto.criar;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
        String descricao,
        Lanche lanche,
        Bebida bebida
) {
}
