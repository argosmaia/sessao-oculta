package com.api.ingresso.dto.listar;

import com.api.ingresso.domain.entities.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ListarAssentoDTO(
        @NotNull UUID id,
        @NotBlank String codigo,
        @NotNull Usuario usuario,
        @NotNull Sessao sessao,
        @NotNull Sala sala
) {
    public ListarAssentoDTO(Assento assento) {
        this(
                assento.getId(),
                assento.getCodigo(),
                assento.getUsuario(),
                assento.getSessao(),
                assento.getSala()
        );
    }
}
