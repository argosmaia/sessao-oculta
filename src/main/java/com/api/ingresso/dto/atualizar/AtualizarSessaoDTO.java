package com.api.ingresso.dto.atualizar;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AtualizarSessaoDTO(
    @NotNull UUID id,
    UUID filmeId,          // opcional: só atualizar se não for null
    @NotNull UUID salaId,
    @NotNull LocalDateTime horario
) {
}