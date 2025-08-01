package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;
import com.api.ingresso.factory.FilmeFactory;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.domain.entities.Filme;

@Component
public class FilmeSeeder {
    private final FilmeRepository filmes;
    private final FilmeFactory filmesFac;

    public FilmeSeeder(FilmeRepository filmes, FilmeFactory filmesFac) {
        this.filmes = filmes;
        this.filmesFac = filmesFac;
    }

    public void run() {
        var filmesDTO = filmesFac.criarFilmes();

        filmesDTO.forEach(dto -> filmes.save(new Filme(dto)));

        System.out.println("🎬 5 filmes foram inseridos no banco.");
    }
}