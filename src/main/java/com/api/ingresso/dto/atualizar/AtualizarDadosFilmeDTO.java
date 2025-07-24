package com.api.ingresso.dto.atualizar;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record AtualizarDadosFilmeDTO(
        @NotNull UUID id,
        String nome,
        String genero,
        String subgenero,
        String descricao,
        String capaUrl, // Link da imagem do pôster/capa
        Double notaMedia, // Avaliação média (opcional, se aplicável)
        LocalDate dataLancamento,
        String diretor
) {
}
