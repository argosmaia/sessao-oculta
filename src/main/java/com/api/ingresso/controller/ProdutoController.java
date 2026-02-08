package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.ingresso.dto.ProdutoDTO;
import com.api.ingresso.dto.atualizar.AtualizarProdutoDTO;
import com.api.ingresso.dto.criar.CriarProdutoDTO;
import com.api.ingresso.dto.listar.ListarProdutoDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<ProdutoDTO>> cadastrarProduto(
            @RequestBody @Valid CriarProdutoDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var resposta = produtoService.cadastrarProduto(dados);
        var uri = uriBuilder.path("/produto/{id}")
                .buildAndExpand(resposta.getDados().id())
                .toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarProdutoDTO>>> listarProdutos(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(produtoService.listarProdutos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ProdutoDTO>> buscarProduto(@PathVariable UUID id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<APIResponse<ProdutoDTO>> atualizarProduto(
            @RequestBody @Valid AtualizarProdutoDTO dados) {
        return ResponseEntity.ok(produtoService.atualizarProduto(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluirProduto(@PathVariable UUID id) {
        var resposta = produtoService.excluir(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
