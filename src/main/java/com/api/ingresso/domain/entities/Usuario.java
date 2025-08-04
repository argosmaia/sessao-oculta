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

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the idade
	 */
	public int getIdade() {
		return idade;
	}

	/**
	 * @param idade the idade to set
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	/**
	 * @return the aniversario
	 */
	public LocalDate getAniversario() {
		return aniversario;
	}

	/**
	 * @param aniversario the aniversario to set
	 */
	public void setAniversario(LocalDate aniversario) {
		this.aniversario = aniversario;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void atualizarDados(AtualizarDadosUsuarioDTO dados) {
		this.nome = dados.nome() != null ? dados.nome() : this.nome; // Se nome for nulo, mantém o atual
		this.telefone = dados.telefone() != null ? dados.telefone() : this.telefone;
		if (dados.endereco() != null) {
            this.endereco.atualizarEndereco(dados.endereco());
        }
	}
	
}
