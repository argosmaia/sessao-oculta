package com.api.ingresso.factory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.api.ingresso.dto.criar.CriarFilmeDTO;

// "play-lh.googleusercontent.com/ooMpCgZK3ftSd8b5z8pob30iilVw2sUf6V9DQoWPRd4UlvWhT-PJIJMgJEojH3WjTXt4srbfzUuEYu72J-Q",


@Component
public class SalaFactory {

    public List<CriarSalaDTO> criarFilmes() {
        return List.of(
            new CriarSalaDTO(
                "O Poderoso Chefão",
                "Drama",
                "Crime",
                "A história da família mafiosa Corleone nos EUA.",
                "2h55min",
                "play-lh.googleusercontent.com/ooMpCgZK3ftSd8b5z8pob30iilVw2sUf6V9DQoWPRd4UlvWhT-PJIJMgJEojH3WjTXt4srbfzUuEYu72J-Q",
                9.2,
                LocalDate.of(1972, 3, 24),
                "Inglês",
                "Francis Ford Coppola",
                "EUA",
                List.of("Marlon Brando", "Al Pacino", "James Caan")
            ),
            new CriarSalaDTO(
                "Parasita",
                "Drama",
                "Thriller",
                "Uma família pobre se infiltra na casa de uma família rica.",
                "2h12min",
                "https://exemplo.com/parasita.jpg",
                8.6,
                LocalDate.of(2019, 5, 30),
                "Coreano",
                "Bong Joon-ho",
                "Coreia do Sul",
                List.of("Song Kang-ho", "Choi Woo-shik", "Park So-dam")
            ),
            new CriarSalaDTO(
                "Cidade de Deus",
                "Drama",
                "Crime",
                "A história da ascensão do crime organizado nas favelas do Rio.",
                "2h10min",
                "https://exemplo.com/cidadededeus.jpg",
                8.7,
                LocalDate.of(2002, 8, 30),
                "Português",
                "Fernando Meirelles",
                "Brasil",
                List.of("Alexandre Rodrigues", "Leandro Firmino", "Seu Jorge")
            ),
            new CriarSalaDTO(
                "Spirited Away",
                "Animação",
                "Fantasia",
                "Uma garota entra num mundo mágico após seus pais se transformarem em porcos.",
                "2h5min",
                "https://exemplo.com/spirited.jpg",
                8.6,
                LocalDate.of(2001, 7, 20),
                "Japonês",
                "Hayao Miyazaki",
                "Japão",
                List.of("Rumi Hiiragi", "Miyu Irino", "Mari Natsuki")
            ),
            new CriarSalaDTO(
                "Matrix",
                "Ficção Científica",
                "Ação",
                "Um hacker descobre que vive numa realidade simulada controlada por máquinas.",
                "2h16min",
                "https://exemplo.com/matrix.jpg",
                8.7,
                LocalDate.of(1999, 3, 31),
                "Inglês",
                "Lana Wachowski, Lilly Wachowski",
                "EUA",
                List.of("Keanu Reeves", "Laurence Fishburne", "Carrie-Anne Moss")
            )
        );
    }
}