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

import com.api.ingresso.dto.FilmeDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosFilmeDTO;
import com.api.ingresso.dto.criar.CriarFilmeDTO;
import com.api.ingresso.dto.listar.ListarFilmesDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.FilmeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	private final FilmeService filmeService;

	public FilmeController(FilmeService filmeService) {
		this.filmeService = filmeService;
	}

    @PostMapping
	public ResponseEntity<APIResponse<FilmeDTO>> cadastrarFilme(
	    @RequestBody @Valid CriarFilmeDTO dados,
	    UriComponentsBuilder uriBuilder
	) {

	    var resposta = filmeService.cadastrarFilme(dados);

	    var uri = uriBuilder.path("/usuario/{id}")
	            .buildAndExpand(resposta.getDados().id())
	            .toUri();

	    return ResponseEntity
					.created(uri)
					.body(resposta);
	}
    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarFilmesDTO>>> listarFilmes(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return ResponseEntity
			.ok(filmeService
				.listarFilmes(paginacao));
    }

    @PutMapping
    public ResponseEntity<APIResponse<FilmeDTO>> atualizarFilme(
            @RequestBody @Valid AtualizarDadosFilmeDTO dados) {
        return ResponseEntity
			.ok(filmeService
				.atualizarFilme(dados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<?>> excluirFilme(@PathVariable UUID id) {
        var resposta = filmeService.excluirFilme(id);
        if (resposta.getStatus() == 404) {
            return ResponseEntity.status(404).body(resposta);
        }
        return ResponseEntity.status(204).body(resposta);
    }
}
