/**
 * 
 */
package com.api.ingresso.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Endereco;
import com.api.ingresso.domain.entities.Usuario;

/**
 * Record de detalhamento para enviar dados de layer a outros
 */
public record UsuarioDTO(
	UUID id,
	String nome,
	LocalDate aniversario,
	String cpf,
	String email,
	String senha,
	String telefone,
	EnderecoDTO endereco
) {
	public UsuarioDTO(Usuario usuario) {
		this(
				usuario.getId(), 
				usuario.getNome(),
				usuario.getAniversario(), 
				usuario.getCpf(),
				usuario.getEmail(), 
				usuario.getSenha(), 
				usuario.getTelefone(), 
				new EnderecoDTO(usuario.getEndereco())
		);
	}
}
