/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.util.UUID;

import com.api.ingresso.domain.embeddable.Endereco;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
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
@Entity(name = "Sala")
@Table(name = "salas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Sala {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    @Embedded
    private Endereco endereco;
}
