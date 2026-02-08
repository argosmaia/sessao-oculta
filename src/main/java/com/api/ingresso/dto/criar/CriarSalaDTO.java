package com.api.ingresso.dto.criar;

import com.api.ingresso.dto.EnderecoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarSalaDTO(
    @NotBlank String nome,
    @NotNull @Valid EnderecoDTO endereco
) {
    
}
