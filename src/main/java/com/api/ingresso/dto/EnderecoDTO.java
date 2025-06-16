package com.api.ingresso.dto;

import com.api.ingresso.domain.embeddable.Endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EnderecoDTO(
	@NotBlank
    String logradouro,
    @NotBlank
    String bairro,
    @NotBlank
    @Pattern(regexp = "\\d{8}")
    String cep,
    @NotBlank
    String cidade,
    @NotBlank
    String uf,
    String complemento,
    @NotBlank
    String numero
) {

    public EnderecoDTO(Endereco endereco) {
        this(
                endereco.getLogradouro(),
                endereco.getBairro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }

}
