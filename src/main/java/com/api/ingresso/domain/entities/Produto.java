/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;

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
}
