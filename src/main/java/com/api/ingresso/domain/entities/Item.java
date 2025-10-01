/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.util.UUID;

import com.api.ingresso.dto.ItemEntradaDTO;
import com.api.ingresso.dto.ItemRespostaDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Item {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;
    private int quantidade;

    public Item(ItemEntradaDTO itens, Produto produto, Pedido pedido) {
        this.id = itens.id();
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = itens.quantidade();
    }

    public Item(ItemRespostaDTO itemSaida, Produto produto, Pedido pedido) {
        this.id = itemSaida.id();
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = itemSaida.quantidade();
    }
}
