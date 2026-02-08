package com.api.ingresso.dto;

import com.api.ingresso.domain.entities.Filme;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record FilmeDTO(
    UUID id,
    String nome,
    String genero,
    String subgenero,
    String descricao,
    String duracao,
    String capaUrl, // Link da imagem do pôster/capa
    Double notaMedia, // Avaliação média (opcional, se aplicável)
    LocalDate dataLancamento,
    String idiomaOriginal,
    String diretor,
    String paisOrigem,
    List<String> elenco
) {
    public FilmeDTO(Filme filme) {
        this(
                filme.getId(),
                filme.getNome(),
                filme.getGenero(),
                filme.getSubgenero(),
                filme.getDescricao(),
                filme.getDuracao(),
                filme.getCapaUrl(),
                filme.getNotaMedia(),
                filme.getDataLancamento(),
                filme.getIdiomaOriginal(),
                filme.getDiretor(),
                filme.getPaisOrigem(),
                filme.getElenco()
        );
    }
}
