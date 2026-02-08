package com.api.ingresso.dto.listar;

import java.time.LocalDateTime;
import java.util.UUID;

import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.dto.FilmeDTO;
import com.api.ingresso.dto.SalaDTO;

public record ListarSessaoDTO(
    UUID id,
    FilmeDTO filme,
    SalaDTO sala,
    LocalDateTime horario
) {
    public ListarSessaoDTO(Sessao sessao) {
        this(
            sessao.getId(),
            new FilmeDTO(sessao.getFilme()),
            new SalaDTO(sessao.getSala()),
            sessao.getHorario().toInstant(null)
                .atZone(java.time.ZoneId.systemDefault())
                .toLocalDateTime()
        );
    }
}
