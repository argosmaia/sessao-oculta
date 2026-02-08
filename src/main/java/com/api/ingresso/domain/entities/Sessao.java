/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import com.api.ingresso.dto.atualizar.AtualizarSessaoDTO;
import com.api.ingresso.dto.criar.CriarSessaoDTO;

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
@Entity(name = "Sessao")
@Table(name = "sessoes")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Sessao {
    @Id 
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;

    @ManyToOne(optional = false)
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    private LocalDateTime horario;

    // CONSTRUTOR COMUM
    public Sessao(Filme filme, Sala sala, LocalDateTime horario) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
    }


    public Sessao(CriarSessaoDTO dados, Filme filme, Sala sala) {
        this.filme = filme;      // entidade já carregada no service pelo UUID
        this.sala = sala;        // idem
        this.horario = dados.horario();
    }

    public void atualizarDados(AtualizarSessaoDTO dados, Filme filme, Sala sala) {
        this.filme = filme != null ? filme : this.filme; // Se nome for nulo, mantém o atual
		this.sala = sala != null ? sala : this.sala;
        this.horario = dados.horario() != null ? dados.horario() : this.horario;
    }
}
