package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Produto;
import com.api.ingresso.dto.ProdutoDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.api.ingresso.dto.atualizar.AtualizarProdutoDTO;
import com.api.ingresso.dto.criar.CriarProdutoDTO;
import com.api.ingresso.dto.listar.ListarProdutoDTO;
import com.api.ingresso.repository.ProdutoRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
    private final ProdutoRepository produtos;

    public ProdutoService(ProdutoRepository produtos) {
        this.produtos = produtos;
    }

    @Transactional
    public APIResponse<ProdutoDTO> cadastrarProduto(CriarProdutoDTO dados) {
        var produto = new Produto(dados); // direto
        produtos.save(produto);
        return APIResponse.criado("Produto criado com sucesso", new ProdutoDTO(produto));
    }


    public APIResponse<Page<ListarProdutoDTO>> listarProdutos(Pageable paginacao) {
        var paginas = produtos.findAll(paginacao).map(ListarProdutoDTO::new);
        return APIResponse.sucesso("Lista de produtos", paginas);
    }

    public APIResponse<ProdutoDTO> buscarPorId(UUID id) {
        var produto = produtos.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return APIResponse.sucesso("Produto encontrado", new ProdutoDTO(produto));
    }

    @Transactional
    public APIResponse<ProdutoDTO> atualizarProduto(AtualizarProdutoDTO dados) {
        var produto = produtos.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        produto.atualizarDados(dados);
        return APIResponse.sucesso("Produto atualizado com sucesso", new ProdutoDTO(produto));
    }

    @Transactional
    public APIResponse<?> excluir(UUID id) {
        if (!produtos.existsById(id)) {
            return APIResponse.naoEncontrado("Produto não foi encontrado");
        }
        produtos.deleteById(id);
        return APIResponse.semConteudo("Produto removido com sucesso");
    }
}
