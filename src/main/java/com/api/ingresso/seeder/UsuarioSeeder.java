package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.entities.Usuario;
import com.api.ingresso.factory.UsuarioFactory;
import com.api.ingresso.repository.UsuarioRepository;


@Component
public class UsuarioSeeder {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioFactory usuarioFactory;

    public UsuarioSeeder(UsuarioRepository usuarioRepository, UsuarioFactory usuarioFactory) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioFactory = usuarioFactory;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            var dados = usuarioFactory.criarUsuarioDTOAleatorio();
            usuarioRepository.save(new Usuario(dados));
        }
        System.out.println("✅ 10 usuários foram inseridos no banco.");
    }
}