/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import com.api.ingresso.dto.atualizar.AtualizarProdutoDTO;
import com.api.ingresso.dto.criar.CriarProdutoDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Entity(name = "Item")
@Table(name = "itens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Produto {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private String descricao;
	@Enumerated(EnumType.STRING)
	private Lanche lanche;
	@Enumerated(EnumType.STRING)
	private Bebida bebida;
	private BigDecimal preco;

	public Produto(CriarProdutoDTO dados) {
		this.nome = dados.nome();
		this.descricao = dados.descricao();
		this.lanche = dados.lanche();
		this.bebida = dados.bebida();
		this.preco = dados.preco();
	}

	public void atualizarDados(AtualizarProdutoDTO dadosAtualizarProdutoDTO) {
		this.nome = dadosAtualizarProdutoDTO.nome() != null ? dadosAtualizarProdutoDTO.nome() : this.nome;
		this.descricao = dadosAtualizarProdutoDTO.descricao() != null ? dadosAtualizarProdutoDTO.descricao() : this.descricao;
		this.lanche =  dadosAtualizarProdutoDTO.lanche() != null ? dadosAtualizarProdutoDTO.lanche() : this.lanche;
		this.bebida =  dadosAtualizarProdutoDTO.bebida() != null ? dadosAtualizarProdutoDTO.bebida() : this.bebida;
		this.preco =  dadosAtualizarProdutoDTO.preco() != null ? dadosAtualizarProdutoDTO.preco() : this.preco;
	}
}
