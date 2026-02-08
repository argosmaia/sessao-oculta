package com.api.ingresso.dto;

import java.util.UUID;

import com.api.ingresso.domain.entities.Sala;

public record SalaDTO(
    UUID id,
    String nome,
    EnderecoDTO endereco
) {
    public SalaDTO(Sala sala) {
        this(sala.getId(), sala.getNome(), new EnderecoDTO(sala.getEndereco()));
    }
}
