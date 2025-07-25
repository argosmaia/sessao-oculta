package com.api.ingresso.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemRespostaDTO(
    @NotBlank String nomeProduto,
    @NotNull int quantidade,
    @NotNull BigDecimal precoUnitario,
    @NotNull BigDecimal precoTotal
) {}