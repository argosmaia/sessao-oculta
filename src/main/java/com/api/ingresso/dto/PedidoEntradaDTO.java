package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoEntradaDTO(
    @NotNull UUID id,
    @NotNull Usuario usuarioId,
    @NotNull Filme filmeId,
    @NotNull Sala salaId,
    @NotNull Sessao sessaoId,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemEntradaDTO> itens,
    @NotNull BigDecimal valor
) {
    public PedidoEntradaDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getUsuario(),
            pedido.getFilme(),
            pedido.getSala(),
            pedido.getSessao(),
            pedido.getMetodoPagamento(),
            pedido.getItens().stream().map(ItemEntradaDTO::new).toList(),
            pedido.getValor()
        );
    }
}
