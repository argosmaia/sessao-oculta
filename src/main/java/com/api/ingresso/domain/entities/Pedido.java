/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.atualizar.AtualizarPedidoRespostaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoRespostaDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
    @ManyToOne(optional = false) @JoinColumn(name = "ingresso_id")
    private Ingresso ingresso;
    @OneToMany(mappedBy = "itens", cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id", nullable = false)
    private List<Item> itens = new ArrayList<>();
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    private Map<UUID, Produto> produtosMap = new HashMap<>();


    public Pedido(CriarPedidoEntradaDTO pedidoEntrada) {
        /**
         *  @NotNull UUID usuarioId,
         *  @NotNull UUID filmeId,
         *  @NotNull UUID salaId,
         *  @NotNull UUID sessaoId,
         *  @NotNull MetodoPagamento metodoPagamento,
         *  @NotNull @Valid List<@Valid ItemEntradaDTO> itens
        */
        this.ingresso = pedidoEntrada.ingresso();
        this.metodoPagamento = pedidoEntrada.metodoPagamento();
        this.itens = pedidoEntrada.itens().stream()
        .map(itemDTO -> {
            Produto produto = produtosMap.get(itemDTO.produtoId());
            if (produto == null) {
                throw new IllegalArgumentException("Produto não encontrado: " + itemDTO.produtoId());
            }
            return new Item(itemDTO, produto);
        })
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

    public void atualizarDadosSaida(AtualizarPedidoRespostaDTO dadosSaida) {
        this.metodoPagamento = dadosSaida.metodoPagamento() != null ? dadosSaida.metodoPagamento() : this.metodoPagamento;
    }


}
