package com.api.ingresso.controller;

import com.api.ingresso.dto.AssentoDTO;
import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.dto.atualizar.AtualizarAssentoDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;
import com.api.ingresso.dto.criar.CriarAssentoDTO;
import com.api.ingresso.dto.listar.ListarAssentoDTO;
import com.api.ingresso.dto.listar.ListarUsuariosDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.AssentoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/assentos")
public class AssentoController {

    private final AssentoService assentoService;

    public AssentoController(AssentoService assentoService) {
        this.assentoService = assentoService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<AssentoDTO>> cadastrarAssento(
            @RequestBody @Valid CriarAssentoDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var resposta = assentoService.cadastrarAssento(dados);

        var uri = uriBuilder.path("/assentos/{id}")
                .buildAndExpand(resposta.getDados().id())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(resposta);
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarAssentoDTO>>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(assentoService.listarAssentos(paginacao));
    }

    @PutMapping
    public ResponseEntity<APIResponse<AssentoDTO>> atualizarAssento(@RequestBody @Valid AtualizarAssentoDTO dados) {
        return ResponseEntity
                .ok(assentoService
                        .atualizarAssento(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluir(@PathVariable UUID id) {
        var resposta = assentoService.excluirAssento(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
