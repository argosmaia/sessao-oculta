package com.api.ingresso.dto.criar;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record CriarFilmeDTO(
        @NotBlank String nome,
        @NotBlank String genero,
        @NotBlank String subgenero,
        @NotBlank String descricao,
        @NotBlank String duracao,
        String capaUrl, // Link da imagem do pôster/capa, opcional
        Double notaMedia, // Avaliação média (opcional, se aplicável)
        @NotNull LocalDate dataLancamento,
        @NotBlank String idiomaOriginal,
        @NotBlank String diretor,
        @NotBlank String paisOrigem,
        List<String> elenco
) {
}
