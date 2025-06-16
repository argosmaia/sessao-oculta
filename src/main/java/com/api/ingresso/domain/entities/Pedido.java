/**
 * 
 */
package com.api.ingresso.domain.entities;

import jakarta.persistence.*;

import java.util.UUID;

/**
 * 
 */
@Entity(name = "Pedido")
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
}
