package com.api.ingresso.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import com.api.ingresso.domain.entities.Sessao;

public record SessaoDTO(
    UUID id,
    FilmeDTO filme,
    SalaDTO sala,
    LocalDateTime horario
) {
    public SessaoDTO(Sessao sessao) {
        this(
            sessao.getId(),
            new FilmeDTO(sessao.getFilme()),
            new SalaDTO(sessao.getSala()),
            sessao.getHorario() // deve ser java.util.Date ou convertida
        );
    }
}
