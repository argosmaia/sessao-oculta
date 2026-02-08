package com.api.ingresso.factory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.entities.Filme;
import com.api.ingresso.domain.entities.Sala;
import com.api.ingresso.dto.criar.CriarSessaoDTO;

@Component
public class SessaoFactory {

    // Gera horários aleatórios para sessões
    private LocalDateTime gerarHorarioAleatorio() {
        int ano = 2026;
        int mes = ThreadLocalRandom.current().nextInt(1, 13);
        int dia = ThreadLocalRandom.current().nextInt(1, 28);
        int hora = ThreadLocalRandom.current().nextInt(10, 23);
        int minuto = ThreadLocalRandom.current().nextInt(0, 60);
        return LocalDateTime.of(ano, mes, dia, hora, minuto);
    }

    // Cria um DTO de sessão aleatório a partir de listas de filmes e salas
    public CriarSessaoDTO criarSessaoDTOAleatorio(List<Filme> filmes, List<Sala> salas) {
        Filme filme = filmes.get(ThreadLocalRandom.current().nextInt(filmes.size()));
        Sala sala = salas.get(ThreadLocalRandom.current().nextInt(salas.size()));
        LocalDateTime horario = gerarHorarioAleatorio();

        return new CriarSessaoDTO(filme.getId(), sala.getId(), horario);
    }
}
