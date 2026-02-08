package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import com.api.ingresso.domain.entities.Produto;

public record ProdutoDTO(
	UUID id,
	String nome,
	String descricao,
	Lanche lanche,
	Bebida bebida,
	BigDecimal preco

) {
    public ProdutoDTO(Produto produto) {
        this(produto.getId(), 
            produto.getNome(), 
            produto.getDescricao(), 
            produto.getLanche(), 
            produto.getBebida(), 
            produto.getPreco()
        );
    }
}
