/**
 * 
 */
package com.api.ingresso.domain.embeddable;

import com.api.ingresso.dto.EnderecoDTO;

/**
 * 
 */
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;
    
    
    /**
	 * @param logradouro = rua
	 * @param bairro
	 * @param cep
	 * @param numero
	 * @param complemento
	 * @param cidade
	 * @param uf
	 */
    public Endereco(EnderecoDTO dados) {
        this.logradouro = dados.logradouro();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }


	public void atualizarEndereco(EnderecoDTO endereco) {
		this.logradouro = endereco.logradouro() != null ? endereco.logradouro() : this.logradouro; // se o logradouro for nulo, mantém o atual
        this.bairro = endereco.bairro() != null ? endereco.bairro() : this.bairro; // se o bairro for nulo, mantém o atual
        this.cep = endereco.cep() != null ? endereco.cep() : this.cep; // se o cep for nulo, mantém o atual
        this.numero = endereco.numero() != null ? endereco.numero() : this.numero; // se o numero for nulo, mantém o atual
        this.complemento = endereco.complemento() != null ? endereco.complemento() : this.complemento; // se o complemento for nulo, mantém o atual
        this.cidade = endereco.cidade() != null ? endereco.cidade() : this.cidade; // se a cidade for nula, mantém a atual
        this.uf = endereco.uf() != null ? endereco.uf() : this.uf; // se a uf for nula, mantém a atual
    }
}