/**
 * 
 */
package com.api.ingresso.dto.criar;

import java.util.List;
import java.util.UUID;

import com.api.ingresso.dto.ItemEntradaDTO;

import jakarta.validation.Valid;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record CriarPedidoEntradaDTO(
    @NotNull Usuario usuarioId,
    @NotNull Filme filmeId,
    @NotNull Sala salaId,
    @NotNull Sessao sessaoId,
    @NotNull MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens
) {}