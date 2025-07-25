package com.api.ingresso.dto;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record ItemEntradaDTO(
    @NotNull UUID produtoId,
    @NotNull int quantidade
) {
    
}
