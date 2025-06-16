/**
 * 
 */
package com.api.ingresso.dto.atualizar;

import java.util.UUID;

import com.api.ingresso.dto.EnderecoDTO;

import jakarta.validation.constraints.NotNull;

/**
 * 
 */
public record AtualizarDadosUsuarioDTO(
		@NotNull UUID id,
		String nome,
		String telefone,
		EnderecoDTO endereco
) {

}
