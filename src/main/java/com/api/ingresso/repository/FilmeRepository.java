package com.api.ingresso.repository;

import java.util.Optional;

import com.api.ingresso.domain.entities.Filme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FilmeRepository extends JpaRepository<Filme, UUID> {
    Optional<Filme> findByNomeIgnoreCase(String nome);
}
