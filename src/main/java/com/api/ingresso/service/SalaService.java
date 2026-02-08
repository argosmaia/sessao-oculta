package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.dto.criar.CriarSalaDTO;
import com.api.ingresso.dto.atualizar.AtualizarSalaDTO;
import com.api.ingresso.dto.listar.ListarSalaDTO;
import com.api.ingresso.repository.SalaRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SalaService {

    private final SalaRepository salas;

    public SalaService(SalaRepository salas) {
        this.salas = salas;
    }

    @Transactional
    public APIResponse<ListarSalaDTO> cadastrarSala(CriarSalaDTO dados) {
        var sala = new Sala(dados);
        salas.save(sala);
        return APIResponse.criado("Sala criada com sucesso", new ListarSalaDTO(sala));
    }

    public APIResponse<Page<ListarSalaDTO>> listarSalas(Pageable paginacao) {
        var paginas = salas.findAll(paginacao).map(ListarSalaDTO::new);
        return APIResponse.sucesso("Lista de salas", paginas);
    }

    public APIResponse<ListarSalaDTO> buscarPorId(UUID id) {
        var sala = salas.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));
        return APIResponse.sucesso("Sala encontrada", new ListarSalaDTO(sala));
    }

    @Transactional
    public APIResponse<ListarSalaDTO> atualizarSala(AtualizarSalaDTO dados) {
        var sala = salas.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Sala não encontrada"));
        sala.atualizarDados(dados);
        return APIResponse.sucesso("Sala atualizada com sucesso", new ListarSalaDTO(sala));
    }

    @Transactional
    public APIResponse<?> excluir(UUID id) {
        if (!salas.existsById(id)) {
            return APIResponse.naoEncontrado("Sala não encontrada");
        }
        salas.deleteById(id);
        return APIResponse.semConteudo("Sala removida com sucesso");
    }
}
