/**
 * 
 */
package com.api.ingresso.dto.listar;

import java.time.LocalDate;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Endereco;
import com.api.ingresso.domain.entities.Usuario;

/**
 * 
 */
public record ListarUsuariosDTO(
	UUID id,
	String nome,
	LocalDate aniversario,
	String cpf,
	String email,
	String telefone,
	Endereco endereco
) {
	public ListarUsuariosDTO(Usuario usuario) {
	    this(
	        usuario.getId(),
	        usuario.getNome(),
	        usuario.getAniversario(),
	        usuario.getCpf(),
	        usuario.getEmail(),
	        usuario.getTelefone(),
	        usuario.getEndereco()
	    );
	}

}
