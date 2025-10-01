/**
 * 
 */
package com.api.ingresso.domain.entities;
import java.time.LocalDate;
import java.util.UUID;

import com.api.ingresso.domain.embeddable.Endereco;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;
import com.api.ingresso.dto.criar.CriarUsuarioDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */

@Table(name = "usuarios")
@Entity(name = "Usuario")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Usuario {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private int idade;
	private LocalDate aniversario;
	@Column(unique = true, nullable = false)
	private String cpf;
	@Column(unique = true, nullable = false)
	private String email;
	private String senha;
	private String telefone;
	@Embedded
    private Endereco endereco;

	public Usuario(CriarUsuarioDTO dados) {
		this.nome = dados.nome();
		this.aniversario = dados.aniversario();
		this.cpf = dados.cpf();
		this.email = dados.email();
		this.senha = dados.senha();
		this.telefone = dados.telefone();
		this.endereco = new Endereco(dados.endereco());
	}

	public void atualizarDados(AtualizarDadosUsuarioDTO dados) {
		this.nome = dados.nome() != null ? dados.nome() : this.nome; // Se nome for nulo, mantém o atual
		this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
		if (dados.endereco() != null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }
	}
	
}
