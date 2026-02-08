package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ingresso.dto.criar.CriarSessaoDTO;
import com.api.ingresso.dto.atualizar.AtualizarSessaoDTO;
import com.api.ingresso.dto.listar.ListarSessaoDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.SessaoService;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/sessoes")
public class SessaoController {

    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<ListarSessaoDTO>> criarSessao(
            @RequestBody @Valid CriarSessaoDTO dados,
            UriComponentsBuilder uriBuilder) {

        var resposta = sessaoService.criarSessao(dados);

        var uri = uriBuilder.path("/sessoes/{id}")
                .buildAndExpand(resposta.getDados().id())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarSessaoDTO>>> listarSessoes(
            @PageableDefault(size = 10, sort = {"horario"}) Pageable paginacao) {
        return ResponseEntity.ok(sessaoService.listarSessoes(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ListarSessaoDTO>> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(sessaoService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<APIResponse<ListarSessaoDTO>> atualizarSessao(
            @RequestBody @Valid AtualizarSessaoDTO dados) {
        return ResponseEntity.ok(sessaoService.atualizarSessao(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluir(@PathVariable UUID id) {
        var resposta = sessaoService.excluir(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
