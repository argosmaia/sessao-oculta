/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.util.UUID;

import com.api.ingresso.dto.AssentoDTO;
import com.api.ingresso.dto.atualizar.AtualizarAssentoDTO;
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
@Table(name = "assentos")
@Entity(name = "Assento")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Assento {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String codigo;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private Sessao sessao;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    public Assento(AssentoDTO assento) {
        // assento.getId(),
        this.codigo = assento.codigo();
        this.usuario = assento.usuario();
        this.sessao = assento.sessao();
        this.sala = assento.sala();
    }

    public void atualizarDados(AtualizarAssentoDTO dados) {
        /**
         *  @NotBlank String codigo,
         * @NotNull Usuario usuario,
         * @NotNull Sessao sessao,
         * @NotNull Sala sala
        */
        this.codigo = dados.codigo() != null ? dados.codigo() : this.codigo;
        this.usuario = dados.usuario() != null ? dados.usuario() : this.usuario;
        this.sessao = dados.sessao() != null ? dados.sessao() : this.sessao;
        this.sala = dados.sala() != null ? dados.sala() : this.sala;	
    }
}
