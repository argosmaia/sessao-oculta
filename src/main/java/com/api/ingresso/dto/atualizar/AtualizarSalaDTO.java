package com.api.ingresso.dto.atualizar;

import java.util.UUID;

import com.api.ingresso.dto.EnderecoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarSalaDTO(
    @NotNull UUID id,
    @NotBlank String nome,
    @NotNull @Valid EnderecoDTO endereco
) {
    
}
