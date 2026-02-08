package com.api.ingresso.dto.listar;

import java.util.UUID;

import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.dto.EnderecoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ListarSalaDTO(
    @NotNull UUID id,
    @NotBlank String nome,
    @NotNull @Valid EnderecoDTO endereco
) {
    public ListarSalaDTO(Sala sala) {
	    this(
	        sala.getId(),
	        sala.getNome(),
	        new EnderecoDTO(sala.getEndereco())
	    );
	}
}
