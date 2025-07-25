package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.dto.ItemRespostaDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import com.api.ingresso.domain.embeddable.MetodoPagamento;

public record PedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull UUID usuarioId,
    @NotNull UUID filmeId,
    @NotNull UUID salaId,
    @NotNull UUID sessaoId,
    @NotNull BigDecimal valor,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemRespostaDTO> itens
) {}