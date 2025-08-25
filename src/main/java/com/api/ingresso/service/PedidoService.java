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
// import com.api.ingresso.repository.ItemRepository;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.validations.CalculoPedidoValidator;
// import com.api.ingresso.repository.ProdutoRepository;
import com.api.ingresso.validations.ItemValidator;
import com.api.ingresso.validations.PagamentoValidator;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class PedidoService {

    private final PedidoRepository pedidos;
    // private final ItemRepository itens;
    // private final ProdutoRepository produtos;

    public PedidoService(PedidoRepository pedidos //,
        //ItemRepository itens, 
        //ProdutoRepository produtos
    ) {
        this.pedidos = pedidos;
        // this.itens = itens;
        // this.produtos = produtos;
    }
    
    // private void calcularValorTotal(Pedido pedido) {
    //     if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
    //         pedido.setValor(BigDecimal.ZERO);
    //         return;
    //     }

    //     BigDecimal total = BigDecimal.ZERO;

    //     for (Item item : pedido.getItens()) {
    //         if (item.getProduto() != null && item.getProduto().getPreco() != null) {
    //             BigDecimal subtotal = item.getProduto().getPreco()
    //                                     .multiply(BigDecimal.valueOf(item.getQuantidade()));
    //             total = total.add(subtotal);
    //         }
    //     }

    //     pedido.setValor(total);
    // }


    // ------------------------------------------------------------------

    @Transactional
    public Pedido criarPedido(CriarPedidoEntradaDTO dados) {
        // validarItens(dados.itens());
        // validarMetodoPagamento(dados.metodoPagamento());
        ItemValidator.validarItens(dados.itens());
        PagamentoValidator.validarMetodoPagamento(dados.metodoPagamento());
        
        Pedido pedido = new Pedido(dados);
        CalculoPedidoValidator.calcularValorTotal(pedido);
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
        CalculoPedidoValidator.calcularValorTotal(pedido);
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
