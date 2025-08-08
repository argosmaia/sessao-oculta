package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoRespostaDTO;
import com.api.ingresso.repository.PedidoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    private final PedidoRepository pedidos;

    public PedidoService(PedidoRepository pedidos) {
        this.pedidos = pedidos;
    }

    @Transactional
    public Pedido criarPedido(CriarPedidoEntradaDTO dados) {
        var novoPedido = new Pedido(dados);
        return pedidos.save(novoPedido);
    }

    public Page<ListarPedidoEntradaDTO> listarPedidos(Pageable paginacao) {
        return pedidos.findAll(paginacao).map(ListarPedidoEntradaDTO::new);
    }

    @Transactional
    public Pedido atualizarPedido(AtualizarPedidoEntradaDTO dados) {
        var pedido = pedidos.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Pedido não encontrado"));
        pedido.atualizarDadosEntrada(dados);
        return pedido;
    }

    @Transactional
    public void excluirPedido(UUID id) {
        if (!pedidos.existsById(id)) {
            throw new EntityNotFoundException("Pedido não foi encontrado");
        }
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
