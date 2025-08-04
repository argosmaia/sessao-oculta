package com.api.ingresso.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ingresso.domain.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, UUID>{
}
