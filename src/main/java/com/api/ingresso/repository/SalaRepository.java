package com.api.ingresso.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ingresso.domain.entities.Sala;

public interface SalaRepository extends JpaRepository<Sala, UUID>{
    
}
