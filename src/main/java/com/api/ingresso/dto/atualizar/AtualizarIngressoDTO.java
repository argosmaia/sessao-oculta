package com.api.ingresso.dto.atualizar;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.constraints.NotBlank;

public record AtualizarIngressoDTO(
    @NotBlank Usuario usuario,
    @NotBlank Filme filme,
    @NotBlank Sessao sessao,
    @NotBlank Sala sala,
    @NotBlank String hashValidacaoIngresso,
    @NotBlank String qrCodeHashValidacaoIngresso
) {
    
}
