/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoRespostaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ESSA CLASSE SERÁ O TOTAL PEDIDO = (INGRESSOS + LANCHES)
 */
@Entity(name = "Pedido")
@Table(name = "pedidos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne(optional = false)
    private Usuario usuario;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> itens = new ArrayList<>();
    @ManyToOne(optional = false)
    private Filme filme;
    @ManyToOne(optional = false)
    private Sala sala;
    @ManyToOne(optional = false)
    private Sessao sessao;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;

    public Pedido(CriarPedidoEntradaDTO pedidoEntrada) {
        /**
         *  @NotNull UUID usuarioId,
         *  @NotNull UUID filmeId,
         *  @NotNull UUID salaId,
         *  @NotNull UUID sessaoId,
         *  @NotNull MetodoPagamento metodoPagamento,
         *  @NotNull @Valid List<@Valid ItemEntradaDTO> itens
        */
        this.usuario = pedidoEntrada.usuarioId();
        this.filme = pedidoEntrada.filmeId();
        this.sala = pedidoEntrada.salaId();
        this.sessao = pedidoEntrada.sessaoId();
        this.metodoPagamento = pedidoEntrada.metodoPagamento();
        this.itens = pedidoEntrada.itens().stream()
                                    .map(itemDTO -> new Item(itemDTO)) // 'this' é o Pedido atual, implementar sem erro futuramente .map(itemDTO -> new Item(itemDTO, this))
                                    .toList();
    }

    /**
     * ATUALIZAR DADOS DE ENTRADA
     * @NotBlank MetodoPagamento metodoPagamento,
     * @NotNull @Valid List<@Valid ItemEntradaDTO> itens
     */

    public void atualizarDadosEntrada(AtualizarPedidoEntradaDTO dadosEntrada) {
        this.metodoPagamento = dadosEntrada.metodoPagamento() != null ? dadosEntrada.metodoPagamento() : this.metodoPagamento;
    }

    // -------------------------------
    // Conversão de saída
    // -------------------------------

    public Pedido(CriarPedidoRespostaDTO pedidoResposta) {
        /**
         * @NotNull UUID id,
         * @NotNull BigDecimal valor,
         * @NotBlank MetodoPagamento metodoPagamento
        */
        this.id = pedidoResposta.id();
        this.valor = pedidoResposta.valor();
        this.metodoPagamento = pedidoResposta.metodoPagamento();       
    }

}
