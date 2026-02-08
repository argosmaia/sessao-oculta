package com.api.ingresso.seeder;

import java.util.List;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.domain.entities.Sessao;
import com.api.ingresso.dto.criar.CriarSessaoDTO;
import com.api.ingresso.factory.SessaoFactory;
import com.api.ingresso.repository.FilmeRepository;
import com.api.ingresso.repository.SalaRepository;
import com.api.ingresso.repository.SessaoRepository;

@Component
public class SessaoSeeder {

    private final SessaoRepository sessaoRepository;
    private final FilmeRepository filmeRepository;
    private final SalaRepository salaRepository;
    private final SessaoFactory sessaoFactory;

    public SessaoSeeder(SessaoRepository sessaoRepository,
                        FilmeRepository filmeRepository,
                        SalaRepository salaRepository,
                        SessaoFactory sessaoFactory) {
        this.sessaoRepository = sessaoRepository;
        this.filmeRepository = filmeRepository;
        this.salaRepository = salaRepository;
        this.sessaoFactory = sessaoFactory;
    }

    public void run() {
        List<Filme> filmes = filmeRepository.findAll();
        List<Sala> salas = salaRepository.findAll();

        if (filmes.isEmpty() || salas.isEmpty()) {
            System.out.println("⚠️ Não há filmes ou salas suficientes para criar sessões.");
            return;
        }

        for (int i = 0; i < 10; i++) {
            CriarSessaoDTO dto = sessaoFactory.criarSessaoDTOAleatorio(filmes, salas);

            Filme filme = filmeRepository.findById(dto.filmeId()).get();
            Sala sala = salaRepository.findById(dto.salaId()).get();

            Sessao sessao = new Sessao(filme, sala, dto.horario());
            sessaoRepository.save(sessao);
        }

        System.out.println("✅ 10 sessões foram inseridas no banco.");
    }
}
