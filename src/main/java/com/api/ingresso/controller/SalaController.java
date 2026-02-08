package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.ingresso.dto.criar.CriarSalaDTO;
import com.api.ingresso.dto.atualizar.AtualizarSalaDTO;
import com.api.ingresso.dto.listar.ListarSalaDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.SalaService;

import jakarta.validation.Valid;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final SalaService salaService;

    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<ListarSalaDTO>> cadastrarSala(
            @RequestBody @Valid CriarSalaDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var resposta = salaService.cadastrarSala(dados);

        var uri = uriBuilder.path("/salas/{id}")
                .buildAndExpand(resposta.getDados().id())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarSalaDTO>>> listarSalas(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(salaService.listarSalas(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<ListarSalaDTO>> buscarSala(@PathVariable UUID id) {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @PutMapping
    public ResponseEntity<APIResponse<ListarSalaDTO>> atualizarSala(
            @RequestBody @Valid AtualizarSalaDTO dados) {
        return ResponseEntity.ok(salaService.atualizarSala(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluirSala(@PathVariable UUID id) {
        var resposta = salaService.excluir(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
