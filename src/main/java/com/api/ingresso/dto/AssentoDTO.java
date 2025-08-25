package com.api.ingresso.dto;

import java.util.UUID;
import com.api.ingresso.domain.entities.Assento;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssentoDTO(
    @NotNull UUID id,
    @NotBlank String codigo,
    @NotBlank Usuario usuario,
    @NotBlank Sessao sessao,
    @NotBlank Sala sala
) {
    public AssentoDTO(Assento assento) {
        this(
            assento.getId(),
            assento.getCodigo(),
            assento.getUsuario(),
            assento.getSessao(),
            assento.getSala()
        );
    }
}
