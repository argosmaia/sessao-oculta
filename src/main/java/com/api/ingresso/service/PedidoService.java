package com.api.ingresso.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Item;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Produto;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.domain.entities.Usuario;
import com.api.ingresso.dto.ItemEntradaDTO;
import com.api.ingresso.dto.ItemRespostaDTO;
import com.api.ingresso.dto.PedidoRespostaDTO;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.atualizar.AtualizarPedidoRespostaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoRespostaDTO;
import com.api.ingresso.dto.listar.ListarPedidoRespostaDTO;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.repository.ProdutoRepository;
import com.api.ingresso.repository.SalaRepository;
import com.api.ingresso.repository.SessaoRepository;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Service
public class PedidoService {
    private final PedidoRepository pedidos;
    private final ProdutoRepository produtos;
    private final UsuarioRepository usuarios;
    private final FilmeRepository filmes;
    private final SalaRepository salas;
    private final SessaoRepository sessoes;

    public PedidoService(
        PedidoRepository pedidos,
        ProdutoRepository produtos,
        UsuarioRepository usuarios,
        FilmeRepository filmes,
        SalaRepository salas,
        SessaoRepository sessoes
    ) {
        this.pedidos = pedidos;
        this.produtos = produtos;
        this.usuarios = usuarios;
        this.filmes = filmes;
        this.salas = salas;
        this.sessoes = sessoes;
    }

    @Transactional public APIResponse<CriarPedidoRespostaDTO> criarPedido(@Valid CriarPedidoEntradaDTO pedidoEntrada) {
        Usuario usuario = usuarios.findById(pedidoEntrada.usuarioId())
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Filme filme = filmes.findById(pedidoEntrada.filme())
            .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        Sala sala = salas.findById(pedidoEntrada.sala())
            .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        Sessao sessao = sessoes.findById(pedidoEntrada.sessaoId())
            .orElseThrow(() -> new RuntimeException("Sessão não encontrada"));    
    
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setFilme(filme);
        pedido.setSala(sala);
        pedido.setSessao(sessao);
        pedido.setMetodoPagamento(pedidoEntrada.metodoPagamento());

        List<Item> itens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;

        for(ItemEntradaDTO itemDTO : pedidoEntrada.itens()) {
            Produto produto = produtos.findById(itemDTO.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
            
            Item item = new Item();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPedido(pedido);

            BigDecimal precoItem = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            valorTotal = valorTotal.add(precoItem);
            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.setValor(valorTotal);
        pedidos.save(pedido);

        CriarPedidoRespostaDTO resposta = new CriarPedidoRespostaDTO(
            pedido.getId(), pedido.getValor(), pedido.getMetodoPagamento());
        

        return APIResponse.criado("Pedido criado com sucesso!", resposta);
    }
    public APIResponse<PedidoRespostaDTO> buscarPorId(UUID id) {
        Pedido pedido = pedidos.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        List<ItemRespostaDTO> itens = pedido.getItens().stream().map(item -> {
            Produto p = item.getProduto();
            BigDecimal precoTotal = p.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            return new ItemRespostaDTO(
                p.getNome(), item.getQuantidade(), p.getPreco(), precoTotal
            );
        }).collect(Collectors.toList());

        PedidoRespostaDTO dto = new PedidoRespostaDTO(
            pedido.getId(),
            pedido.getUsuario().getId(),
            pedido.getFilme().getId(),
            pedido.getSala().getId(),
            pedido.getSessao().getId(),
            pedido.getValor(),
            pedido.getMetodoPagamento(),
            itens
        );

        return APIResponse.sucesso("Pedido encontrado", dto);
    }

    public APIResponse<List<ListarPedidoRespostaDTO>> listarTodos() {
        List<ListarPedidoRespostaDTO> pedidosLista = pedidos.findAll().stream().map(pedido ->
            new ListarPedidoRespostaDTO(
                pedido.getId(), pedido.getValor(), pedido.getMetodoPagamento()
            )
        ).collect(Collectors.toList());

        return APIResponse.sucesso("Lista de pedidos carregada com sucesso", pedidosLista);
    }

    @Transactional
    public APIResponse<AtualizarPedidoRespostaDTO> atualizar(@NotNull UUID id, @Valid AtualizarPedidoEntradaDTO dto) {
        Pedido pedido = pedidos.findById(id)
            .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setMetodoPagamento(dto.metodoPagamento());

        BigDecimal novoValor = BigDecimal.ZERO;
        List<Item> novosItens = new ArrayList<>();

        for (ItemEntradaDTO itemDto : dto.itens()) {
            Produto produto = produtos.findById(itemDto.produtoId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            Item item = new Item();
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPedido(pedido);

            BigDecimal preco = produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade()));
            novoValor = novoValor.add(preco);

            novosItens.add(item);
        }

        pedido.getItens().clear();
        pedido.getItens().addAll(novosItens);
        pedido.setValor(novoValor);
        pedidos.save(pedido);

        return APIResponse.sucesso("Pedido atualizado com sucesso", new AtualizarPedidoRespostaDTO(
            pedido.getId(), pedido.getValor(), pedido.getMetodoPagamento()
        ));
    }
}
