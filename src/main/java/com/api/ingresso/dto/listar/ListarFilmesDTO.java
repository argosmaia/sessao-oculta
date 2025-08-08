package com.api.ingresso.dto.listar;

import com.api.ingresso.domain.entities.Filme;

import java.time.LocalDate;
import java.util.UUID;

public record ListarFilmesDTO(
        UUID id,
        String nome,
        String genero,
        String subgenero,
        String descricao,
        String capaUrl, // Link da imagem do pôster/capa
        Double notaMedia, // Avaliação média (opcional, se aplicável)
        LocalDate dataLancamento,
        String diretor
) {
    public ListarFilmesDTO(Filme filme) {
        this(
                filme.getId(),
                filme.getNome(),
                filme.getGenero(),
                filme.getSubgenero(),
                filme.getDescricao(),
                filme.getCapaUrl(),
                filme.getNotaMedia(),
                filme.getDataLancamento(),
                filme.getDiretor()
        );
    }
}
