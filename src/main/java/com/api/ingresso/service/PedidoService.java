package com.api.ingresso.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.embeddable.MetodoPagamento;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Produto;
import com.api.ingresso.dto.ItemEntradaDTO;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoRespostaDTO;
import com.api.ingresso.repository.ItemRepository;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.repository.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class PedidoService {

    private final PedidoRepository pedidos;
    private final ItemRepository itens;
    private final ProdutoRepository produtos;

    public PedidoService(PedidoRepository pedidos, ItemRepository itens, ProdutoRepository produtos) {
        this.pedidos = pedidos;
        this.itens = itens;
        this.produtos = produtos;
    }

    // HELPERS ADIONAIS QUE IRÃO PARA A PACKAGE VALIDATIONS FUTURAMENTE
    // -----------------------------------------------------------------
    private void validarItens(@Valid List<ItemEntradaDTO> item) {
        if (item == null || item.isEmpty()) throw new IllegalArgumentException("Lista de itens vazia");
        for (ItemEntradaDTO itemDTO : item) {
            if (itemDTO.quantidade() < 1) throw new IllegalArgumentException("Quantidade do item não pode ser zero ou negativa.");
            if (itemDTO == null || itemDTO.preco().compareTo(BigDecimal.ONE) < 0) throw new IllegalArgumentException("Preço precisa ter um vallor de pelo menos R$ 1.");
        }
    }

    private void validarMetodoPagamento(MetodoPagamento metodo) {
    }

    private void calcularValorTotal(Pedido pedido) {}
    // ------------------------------------------------------------------

    @Transactional
    public Pedido criarPedido(CriarPedidoEntradaDTO dados) {
        validarItens(dados.itens());
        validarMetodoPagamento(dados.metodoPagamento());

        Pedido pedido = new Pedido(dados);
        calcularValorTotal(pedido);
        pedidos.save(pedido);

        return pedido;
    }

    public Page<ListarPedidoEntradaDTO> listarPedidos(Pageable paginacao) {
        return pedidos.findAll(paginacao).map(ListarPedidoEntradaDTO::new);
    }

    public Pedido atualizarPedido(UUID id, AtualizarPedidoEntradaDTO dados) {
        var pedido = pedidos.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        
        /**
         * LÓGICA PARA TRATAR ERRO DE DUPLICAÇÃO DE PAGAMENTOS
         * FUTURAMENTE IMPLEMENTAR UM VALIDATION HELPER
         * if(pedido.estaPago()) {
         *      throw new BusinessException("O pedido já está pego e não pode ser alterado");
         * }
         */
        pedido.atualizarDadosEntrada(dados);
        calcularValorTotal(pedido);
        pedidos.save(pedido);
        return pedido;
    }

    @Transactional
    public void excluirPedido(UUID id) {
        // IMPLEMENTAR FUTURAMENTE UMA POLITICA DE EXCLUSÃO APÓS 30 DIAS DO BANCO DE DADOS (EXCLUI PRO USUARIO, MAS A EMPRESA AINDA TEM OS DADOS PARA RECUPERAR)
        if (!pedidos.existsById(id)) throw new EntityNotFoundException("Pedido não foi encontrado");
        pedidos.deleteById(id);
    }

    public Pedido buscarPedido(UUID id) {
        return pedidos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Esse pedido não consta em nossos dados!"));
    }

    public Page<ListarPedidoRespostaDTO> listarPedidosResposta(Pageable paginacao) {
        return pedidos.findAll(paginacao).map(ListarPedidoRespostaDTO::new);
    }
}
