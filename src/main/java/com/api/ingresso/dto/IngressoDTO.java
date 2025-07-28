package com.api.ingresso.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Ingresso;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IngressoDTO(
    @NotNull UUID id,
    @NotBlank Usuario usuario,
    @NotBlank Filme filme,
    @NotBlank Sessao sessao,
    @NotBlank Sala sala,
    @NotBlank String hashValidacaoIngresso,
    @NotBlank String qrCodeHashValidacaoIngresso, // Aqui será a imagem do QRCode do Hash acima para efeito de validação caso necessaŕio
    @NotNull LocalDateTime criadoEm,
    boolean usado
) {
    public IngressoDTO(Ingresso ingresso) {
        this(
            ingresso.getId(), 
            ingresso.getUsuario(),
            ingresso.getFilme(), 
            ingresso.getSessao(),
            ingresso.getSala(), 
            ingresso.getHashValidacaoIngresso(),
            ingresso.getQrCodeHashValidacaoIngresso(),
            ingresso.getCriadoEm(),
            ingresso.isUsado()
        );
    }
}
