package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.dto.criar.CriarSessaoDTO;
import com.api.ingresso.dto.atualizar.AtualizarSessaoDTO;
import com.api.ingresso.dto.listar.ListarSessaoDTO;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.repository.SalaRepository;
import com.api.ingresso.repository.SessaoRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final FilmeRepository filmeRepository;
    private final SalaRepository salaRepository;

    public SessaoService(SessaoRepository sessaoRepository,
                         FilmeRepository filmeRepository,
                         SalaRepository salaRepository) {
        this.sessaoRepository = sessaoRepository;
        this.filmeRepository = filmeRepository;
        this.salaRepository = salaRepository;
    }

    @Transactional
    public APIResponse<ListarSessaoDTO> criarSessao(CriarSessaoDTO dados) {
        Filme filme = filmeRepository.findById(dados.filmeId())
                .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
        Sala sala = salaRepository.findById(dados.salaId())
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));

        Sessao sessao = new Sessao(filme, sala, dados.horario());
        sessaoRepository.save(sessao);

        return APIResponse.criado("Sessão criada com sucesso", new ListarSessaoDTO(sessao));
    }

    public APIResponse<Page<ListarSessaoDTO>> listarSessoes(Pageable paginacao) {
        Page<ListarSessaoDTO> paginas = sessaoRepository
            .findAll(paginacao)
            .map(ListarSessaoDTO::new);

        return APIResponse.sucesso("Lista de sessões", paginas);
    }

    public APIResponse<ListarSessaoDTO> buscarPorId(UUID id) {
        Sessao sessao = sessaoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));

        return APIResponse.sucesso("Sessão encontrada", new ListarSessaoDTO(sessao));
    }

    @Transactional
    public APIResponse<ListarSessaoDTO> atualizarSessao(AtualizarSessaoDTO dados) {
        Sessao sessao = sessaoRepository.findById(dados.id())
            .orElseThrow(() -> new EntityNotFoundException("Sessão não encontrada"));

        // Atualiza sala
        Sala sala = salaRepository.findById(dados.salaId())
            .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));
        sessao.setSala(sala);

        // Atualiza horário
        sessao.setHorario(dados.horario());

        // Atualiza filme se quiser (opcional)
        // if (dados.filmeId() != null) {
        //     Filme filme = filmeRepository.findById(dados.filmeId())
        //         .orElseThrow(() -> new EntityNotFoundException("Filme não encontrado"));
        //     sessao.setFilme(filme);
        // }

        sessaoRepository.save(sessao);
        return APIResponse.sucesso("Sessão atualizada com sucesso", new ListarSessaoDTO(sessao));
    }

    @Transactional
    public APIResponse<?> excluir(UUID id) {
        if (!sessaoRepository.existsById(id)) {
            return APIResponse.naoEncontrado("Sessão não encontrada");
        }
        sessaoRepository.deleteById(id);
        return APIResponse.semConteudo("Sessão removida com sucesso");
    }
}
