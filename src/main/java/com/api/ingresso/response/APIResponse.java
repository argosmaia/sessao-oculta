package com.api.ingresso.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class APIResponse<T> {
	private int status;
	private String mensagem;
	private T dados;
	

	public static <T> APIResponse<T> sucesso(String mensagem, T dados) {
	    return new APIResponse<>(200, mensagem, dados);
	}
	
	public static <T> APIResponse<T> criado(String mensagem, T dados) {
        return new APIResponse<>(201, mensagem, dados);
    }

    public static <T> APIResponse<T> semConteudo(String mensagem) {
        return new APIResponse<>(204, mensagem, null);
    }

    public static <T> APIResponse<T> erro(int status, String mensagem) {
        return new APIResponse<>(status, mensagem, null);
    }

    public static <T> APIResponse<T> erroDados(int status, String mensagem, T dados) {
        return new APIResponse<>(status, mensagem, dados);
    }

    public static <T> APIResponse<T> requisicaoInvalida(String mensagem) {
        return new APIResponse<>(400, mensagem, null);
    }

    public static <T> APIResponse<T> naoAutorizado(String mensagem) {
        return new APIResponse<>(401, mensagem, null);
    }

    public static <T> APIResponse<T> acessoNegado(String mensagem) { // HAHA VTNC CURINTHIA
        return new APIResponse<>(403, mensagem, null);
    }

    public static <T> APIResponse<T> naoEncontrado(String mensagem) {
        return new APIResponse<>(404, mensagem, null);
    }

    public static <T> APIResponse<T> conflito(String mensagem) { // Conflito de dados caso já exista no BD ou no front
        return new APIResponse<>(409, mensagem, null);
    }

    public static <T> APIResponse<T> entidadeInvalida(String mensagem, T dados) { // erro de validação de domínio específico
        return new APIResponse<>(422, mensagem, dados);
    }

    public static <T> APIResponse<T> erroInterno(String mensagem) {
        return new APIResponse<>(500, mensagem, null);
    }

    public static <T> APIResponse<T> naoImplementado(String mensagem) {
        return new APIResponse<>(501, mensagem, null);
    }

    public static <T> APIResponse<T> gatewayIncorreto(String mensagem) {
        return new APIResponse<>(502, mensagem, null);
    }

    public static <T> APIResponse<T> servicoIndisponivel(String mensagem) {
        return new APIResponse<>(503, mensagem, null);
    }

    public static <T> APIResponse<T> tempoLimiteExcedido(String mensagem) {
        return new APIResponse<>(504, mensagem, null);
    }


}
