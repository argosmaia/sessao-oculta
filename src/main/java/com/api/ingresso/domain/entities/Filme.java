/**
 * 
 */
package com.api.ingresso.domain.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.api.ingresso.dto.atualizar.AtualizarDadosFilmeDTO;
import com.api.ingresso.dto.criar.CriarFilmeDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@Table(name = "filmes")
@Entity(name = "Filme")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Filme {
	@Id @GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	private String nome;
	private String genero;
	private String subgenero;
	private String idadeMinima; // implementar em sessão futuramente
	private String descricao;
	private String duracao;
	private String capaUrl;       // Link da imagem do pôster/capa
	private Double notaMedia;     // Avaliação média (opcional, se aplicável)
	private LocalDate dataLancamento;
	private String idiomaOriginal;
	private String diretor;
	private String paisOrigem;
	@ElementCollection private List<String> elenco;


	public Filme(CriarFilmeDTO dados) {
		this.nome = dados.nome();
		this.genero = dados.genero();
		this.subgenero = dados.subgenero();
		this.descricao = dados.descricao();
		this.duracao = dados.duracao();
		this.capaUrl = dados.capaUrl();
		this.notaMedia = dados.notaMedia();
		this.dataLancamento = dados.dataLancamento();
		this.idiomaOriginal = dados.idiomaOriginal();
		this.diretor = dados.diretor();
		this.paisOrigem = dados.paisOrigem();
		this.elenco = dados.elenco();
	}

	public void atualizarDados(AtualizarDadosFilmeDTO dados) {
		/**
		 *         String nome,
		 *         String genero,
		 *         String subgenero,
		 *         String descricao,
		 *         String capaUrl, // Link da imagem do pôster/capa
		 *         double notaMedia, // Avaliação média (opcional, se aplicável)
		 *         LocalDate dataLancamento,
		 *         String diretor
		 */
		this.nome = dados.nome() != null ? dados.nome() : this.nome;
		this.genero = dados.genero() != null ? dados.genero() : this.genero;
		this.subgenero = dados.subgenero() != null ? dados.subgenero() : this.subgenero;
		this.descricao = dados.descricao() != null ? dados.descricao() : this.descricao;
		this.capaUrl = dados.capaUrl() != null ? dados.capaUrl() : this.capaUrl;
		this.notaMedia = dados.notaMedia() != null ? dados.notaMedia() : this.notaMedia;
		this.dataLancamento = dados.dataLancamento() != null ? dados.dataLancamento() : this.dataLancamento;
		this.diretor = dados.diretor() != null ? dados.diretor() : this.diretor;
	}
}
