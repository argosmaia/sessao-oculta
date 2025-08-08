package com.api.ingresso.repository;

import com.api.ingresso.domain.entities.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FilmeRepository extends JpaRepository<Filme, UUID> {
    //Optional<Filme> findByNomeAndId(String nome, UUID id);
}
