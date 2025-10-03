/**
 *
 */
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;
import com.api.ingresso.dto.criar.CriarUsuarioDTO;
import com.api.ingresso.dto.listar.ListarUsuariosDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.UsuarioService;
import jakarta.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<UsuarioDTO>> cadastrarUsuario(
            @RequestBody @Valid CriarUsuarioDTO dados,
            UriComponentsBuilder uriBuilder
    ) {
        var resposta = usuarioService.cadastrarUsuario(dados);

        var uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(resposta.getDados().id())
                .toUri();

        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarUsuariosDTO>>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity.ok(usuarioService.listarUsuarios(paginacao));
    }

    @GetMapping("/buscar")
    public ResponseEntity<APIResponse<UsuarioDTO>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(usuarioService.buscar(nome));
    }

    @PutMapping
    public ResponseEntity<APIResponse<UsuarioDTO>> atualizarUsuario(
            @RequestBody @Valid AtualizarDadosUsuarioDTO dados) {
        return ResponseEntity.ok(usuarioService.atualizarUsuario(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluir(@PathVariable UUID id) {
        var resposta = usuarioService.excluir(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
