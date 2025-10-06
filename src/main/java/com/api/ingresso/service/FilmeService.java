package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.dto.FilmeDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosFilmeDTO;
import com.api.ingresso.dto.criar.CriarFilmeDTO;
import com.api.ingresso.dto.listar.ListarFilmesDTO;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.response.APIResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class FilmeService {
    private final FilmeRepository filmes;
    
    public FilmeService(FilmeRepository filmes) {
        this.filmes = filmes;
    }

    //===== Cadastro de filmes chamando pro front-end /GET localhost:8080/filme
    @Transactional
    public APIResponse<FilmeDTO> cadastrarFilme(CriarFilmeDTO dados) {
        var filme = new Filme(dados);
        filmes.save(filme);
        return APIResponse
                .sucesso("Filme criado com sucesso:\n", 
                    new FilmeDTO(filme));
    }

    public APIResponse<Page<ListarFilmesDTO>> listarFilmes(Pageable paginacao) {
		var paginas = filmes.findAll(paginacao)
				.map(ListarFilmesDTO::new);
		return APIResponse.sucesso("Lista de filmes:\n", paginas);
	}

    public APIResponse<FilmeDTO> buscar(String nome) {
        var filme = filmes.findByNomeIgnoreCase(nome)
                .map(FilmeDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
        return APIResponse.sucesso("Filme encontrado", filme);
    }

    @Transactional
    public APIResponse<FilmeDTO> atualizarFilme(AtualizarDadosFilmeDTO dados) {
        var filme = filmes.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
        filme.atualizarDados(dados);
        return APIResponse.sucesso("Filme atualizado com sucesso", new FilmeDTO(filme));
    }

    @Transactional
    public APIResponse<?> excluirFilme(UUID id) {
        if (!filmes.existsById(id)) {
            return APIResponse.erro(404, "O filme não foi encontrado");
        }
        filmes.deleteById(id);
        return APIResponse.semConteudo("O filme foi removido com sucesso");
    }
}
