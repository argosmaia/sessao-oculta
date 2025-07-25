package com.api.ingresso.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ingresso.domain.entities.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    
}