package com.api.ingresso.domain.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.api.ingresso.dto.atualizar.AtualizarIngressoDTO;
import com.api.ingresso.dto.criar.CriarIngressoDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Ingresso")
@Table(name = "ingressos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class Ingresso {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "filme_id", nullable = false)
    private Filme filme;
    @ManyToOne
    @JoinColumn(name = "sessao_id", nullable = false)
    private Sessao sessao;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
    @Column(nullable = false, unique = true)
    private String hashValidacaoIngresso;
    private String qrCodeHashValidacaoIngresso; // Aqui será a imagem do QRCode do Hash acima para efeito de validação caso necessaŕio
    @CreationTimestamp
    private LocalDateTime criadoEm;
    private boolean usado;

    public Ingresso(CriarIngressoDTO dados) {
        this.usuario = dados.usuario();
        this.filme = dados.filme();
        this.sessao = dados.sessao();
        this.sala = dados.sala();
        this.hashValidacaoIngresso = dados.hashValidacaoIngresso();
        this.qrCodeHashValidacaoIngresso = dados.qrCodeHashValidacaoIngresso();
        this.criadoEm = dados.criadoEm();
        this.usado = dados.usado();
    }

    /**
    * @NotBlank Usuario usuario,
    * @NotBlank Filme filme,
    * @NotBlank Sessao sessao,
    * @NotBlank Sala sala,
    * @NotBlank String hashValidacaoIngresso,
    * @NotBlank String qrCodeHashValidacaoIngresso
    * Serve para atualizar informações na API
    */
    public void atualizarDados(AtualizarIngressoDTO dados) {
		this.usuario = dados.usuario() != null ? dados.usuario() : this.usuario; // Se nome for nulo, mantém o atual
		this.filme = dados.filme() != null ? dados.filme() : this.filme;
        this.sessao = dados.sessao() != null ? dados.sessao() : this.sessao;
        this.sala = dados.sala() != null ? dados.sala() : this.sala;		
        this.hashValidacaoIngresso = dados.hashValidacaoIngresso() != null ? dados.hashValidacaoIngresso() : this.hashValidacaoIngresso;		
        this.qrCodeHashValidacaoIngresso = dados.qrCodeHashValidacaoIngresso() != null ? dados.qrCodeHashValidacaoIngresso() : this.qrCodeHashValidacaoIngresso;		
	}
	
}
