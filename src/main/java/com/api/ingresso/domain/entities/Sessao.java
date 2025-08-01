/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.util.UUID;
import java.util.Date;

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
@Entity(name = "Sessao")
@Table(name = "sessoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Sessao {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Filme sessao;
    private Date horario;
}
