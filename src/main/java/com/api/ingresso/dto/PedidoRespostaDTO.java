package com.api.ingresso.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PedidoRespostaDTO(
    @NotNull UUID id,
    @NotNull Usuario usuarioId,
    @NotNull Filme filmeId,
    @NotNull Sala salaId,
    @NotNull Sessao sessaoId,
    @NotNull BigDecimal valor,
    @NotBlank MetodoPagamento metodoPagamento,
    @NotNull @Valid List<@Valid ItemRespostaDTO> itens
) {
    public PedidoRespostaDTO(Pedido pedido) {
        this(
            pedido.getId(),
            pedido.getUsuario(),
            pedido.getFilme(),
            pedido.getSala(),
            pedido.getSessao(),
            pedido.getValor(),
            pedido.getMetodoPagamento(),
            pedido.getItens().stream().map(ItemRespostaDTO::new).toList()
        );
    }
}