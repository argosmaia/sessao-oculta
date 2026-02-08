package com.api.ingresso.dto.criar;

import java.time.LocalDateTime;
import java.util.UUID;


import jakarta.validation.constraints.NotNull;

public record CriarSessaoDTO(
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull LocalDateTime horario
) {
    
}
