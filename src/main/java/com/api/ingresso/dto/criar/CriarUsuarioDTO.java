/**
 * 
 */
package com.api.ingresso.dto.criar;

import java.time.LocalDate;

import com.api.ingresso.dto.EnderecoDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * 
 */
public record CriarUsuarioDTO(
		@NotBlank String nome,
		@NotNull LocalDate aniversario,
		@NotBlank @Pattern(regexp = "\\d{11}") String cpf,
		@NotBlank @Email String email,
		@NotBlank @Size(min = 8, max = 16) String senha,
		@NotBlank @Size(min = 12, max = 13) String telefone,
		@NotNull @Valid EnderecoDTO endereco
	) {
}