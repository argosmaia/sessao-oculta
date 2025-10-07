package com.api.ingresso.service;

import com.api.ingresso.domain.entities.Assento;
import com.api.ingresso.dto.AssentoDTO;
import com.api.ingresso.dto.FilmeDTO;
import com.api.ingresso.dto.atualizar.AtualizarAssentoDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosFilmeDTO;
import com.api.ingresso.dto.criar.CriarAssentoDTO;
import com.api.ingresso.dto.listar.ListarAssentoDTO;
import com.api.ingresso.dto.listar.ListarFilmesDTO;
import com.api.ingresso.repository.AssentoRepository;
import com.api.ingresso.response.APIResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AssentoService {
    private final AssentoRepository assentoRepository;

    public AssentoService(AssentoRepository assentoRepository) {
        this.assentoRepository = assentoRepository;
    }

    @Transactional
    public APIResponse<AssentoDTO> cadastrarAssento(CriarAssentoDTO  dados) {
        var assento = new Assento();

        assentoRepository.save(assento);

        return APIResponse
                .sucesso("Assento cadastrado com sucesso",
                        new AssentoDTO(assento));
    }

    public APIResponse<Page<ListarAssentoDTO>> listarAssentos(Pageable paginacao) {
        var paginas = assentoRepository.findAll(paginacao)
                .map(ListarAssentoDTO::new);
        return APIResponse.sucesso("Lista de assentos:\n", paginas);
    }

    @Transactional
    public APIResponse<AssentoDTO> atualizarAssento(AtualizarAssentoDTO dados) {
        var assento = assentoRepository.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Assento não encontrado no banco de dados"));
        assento.atualizarDados(dados);
        return APIResponse.sucesso("Assento atualizado com sucesso", new AssentoDTO(assento));
    }

    @Transactional
    public APIResponse<?> excluirAssento(UUID id) {
        if (!assentoRepository.existsById(id)) {
            return APIResponse.erro(404, "O assento não foi encontrado");
        }
        assentoRepository.deleteById(id);
        return APIResponse.semConteudo("O assento foi removido com sucesso");
    }
}
