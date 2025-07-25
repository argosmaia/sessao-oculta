package com.api.ingresso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.domain.entities.Usuario;

@Service
public class UsuarioService {
    @Autowired private UsuarioRepository usuarios;

    public APIResponse<UsuarioDTO> buscar(String nome) {
        var usuario = usuarios
                        .findByNomeIgnoreCase(nome)
                        .orElseThrow(
                            () -> new RuntimeException("Usuário com nome '" + nome + "' não foi encontrado!")
                        );
        return APIResponse.sucesso("Usuário encontrado -> ", new UsuarioDTO(usuario));
    }

    public APIResponse<UsuarioDTO> salvar(Usuario usuario) {
        return usuarios.save(usuario);
    }
}
