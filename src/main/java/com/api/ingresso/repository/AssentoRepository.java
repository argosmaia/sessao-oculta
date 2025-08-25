package com.api.ingresso.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ingresso.domain.entities.Assento;

public interface AssentoRepository extends JpaRepository<Assento, UUID> {
    
}
