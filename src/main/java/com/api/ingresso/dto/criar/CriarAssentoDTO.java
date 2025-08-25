package com.api.ingresso.dto.criar;

import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarAssentoDTO(
    @NotBlank String codigo,
    @NotNull Usuario usuario,
    @NotNull Sessao sessao,
    @NotNull Sala sala
) {
    
}
