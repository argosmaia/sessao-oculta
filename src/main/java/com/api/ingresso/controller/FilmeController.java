package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.dto.FilmeDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosFilmeDTO;
import com.api.ingresso.dto.criar.CriarFilmeDTO;
import com.api.ingresso.dto.listar.ListarFilmesDTO;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired FilmeRepository filmes;

    @PostMapping @Transactional
    public ResponseEntity<APIResponse<?>> cadastrarDadosFilme(@RequestBody @Valid CriarFilmeDTO dados, UriComponentsBuilder uriBuilder) {
        var filme = new Filme(dados);
        filmes.save(filme);
        var uri = uriBuilder
                .path("/filme/{id}")
                .buildAndExpand(filme.getId())
                .toUri();

        return ResponseEntity
                .created(uri)
                .body(APIResponse
                        .criado("Informação sobre o filme foi criado com sucesso", new Filme(dados)
                )
        );
    }

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarFilmesDTO>>> listarFilmes(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao) {
        var paginas = filmes
                .findAll(paginacao)
                .map(ListarFilmesDTO::new);

        return ResponseEntity
                .ok(APIResponse
                                .sucesso("Lista dos filmes: ", paginas)
                        //.sucesso("Dados do filme foi atualizado com sucesso", new FilmeDTO())
                );
    }

    @PutMapping @Transactional
    public ResponseEntity<?> atualizarInformacoes(@RequestBody @Valid AtualizarDadosFilmeDTO dados) {
        try {
            var filme = filmes.getReferenceById(dados.id());
            filme.atualizarDados(dados);
            return ResponseEntity.ok(
                    APIResponse
                            .sucesso(
                                    "Dados do usuário foram atualizados com sucesso",
                                    new FilmeDTO(filme)
                            )
            );
        } catch (EntityNotFoundException erro) {
            return ResponseEntity.status(404)
                    .body(APIResponse
                            .erro(404, erro.getMessage())
                    );
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<APIResponse<?>> excluir(@PathVariable UUID id) {
        if (!filmes.existsById(id)) { // Verifica se o ID existe no banco
            return ResponseEntity
                    .status(404) // Retorna 404 caso não encontre
                    .body(APIResponse
                            .erro(404, "O filme não foi encontrado ou não existe"));
        }

        filmes.deleteById(id); // Deleta o filme

        return ResponseEntity
                .status(204) // Retorna 204 se a exclusão foi bem-sucedida
                .body(APIResponse
                        .semConteudo("O filme e seus dados foram removido com sucesso")
                );
    }
}
