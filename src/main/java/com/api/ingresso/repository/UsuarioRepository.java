/**
 * 
 */
package com.api.ingresso.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.ingresso.domain.entities.Usuario;

/**
 * 
 */
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    Optional<Usuario> findByNomeIgnoreCase(String nome);
}
