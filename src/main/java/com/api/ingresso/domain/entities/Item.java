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
    private Produto produto;
    private int quantidade;

    public Item(ItemEntradaDTO itens, Produto produto, Pedido pedido) {
        this.id = itens.id();
        this.produto = produto;
        this.quantidade = itens.quantidade();
    }


    public Item(ItemRespostaDTO itemSaida, Pedido pedido) { // Para a saída dos dados
        this.id = itemSaida.id();
        this.pedido = pedido;
        this.quantidade = itemSaida.quantidade();
    }
}
