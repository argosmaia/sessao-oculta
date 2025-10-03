package com.api.ingresso.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Usuario;
import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;

import jakarta.persistence.EntityNotFoundException;

import com.api.ingresso.dto.criar.CriarUsuarioDTO;
import com.api.ingresso.dto.listar.ListarUsuariosDTO;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarios;

    public UsuarioService(UsuarioRepository usuarios) {
        this.usuarios = usuarios;
    }

    @Transactional
    public APIResponse<UsuarioDTO> cadastrarUsuario(CriarUsuarioDTO dados) {
        var usuario = new Usuario(dados);
        usuarios.save(usuario);
        return APIResponse.criado("Usuário criado com sucesso", new UsuarioDTO(usuario));
    }

    public APIResponse<Page<ListarUsuariosDTO>> listarUsuarios(Pageable paginacao) {
        var paginas = usuarios.findAll(paginacao).map(ListarUsuariosDTO::new);
        return APIResponse.sucesso("Lista de usuários", paginas);
    }

    public APIResponse<UsuarioDTO> buscar(String nome) {
        var usuario = usuarios.findByNomeIgnoreCase(nome)
                .map(UsuarioDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return APIResponse.sucesso("Usuário encontrado", usuario);
    }

    @Transactional
    public APIResponse<UsuarioDTO> atualizarUsuario(AtualizarDadosUsuarioDTO dados) {
        var usuario = usuarios.findById(dados.id())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        usuario.atualizarDados(dados);
        return APIResponse.sucesso("Usuário atualizado com sucesso", new UsuarioDTO(usuario));
    }

    @Transactional
    public APIResponse<?> excluir(UUID id) {
        if (!usuarios.existsById(id)) {
            return APIResponse.naoEncontrado("Usuário não foi encontrado");
        }
        usuarios.deleteById(id);
        return APIResponse.semConteudo("O usuário foi removido com sucesso");
    }
}
