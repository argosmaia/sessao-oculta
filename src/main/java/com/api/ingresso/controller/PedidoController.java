package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.dto.PedidoEntradaDTO;
import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.criar.CriarPedidoRespostaDTO;
import com.api.ingresso.dto.listar.ListarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoRespostaDTO;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.response.APIResponse;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;

    public PedidoController(PedidoRepository pedidos) {
        this.pedidos = pedidos;
    }

	// DADOS DE ENTRADA

    // Criar pedido
    @PostMapping
    @Transactional
    public ResponseEntity<APIResponse<?>> cadastrarPedido(@RequestBody @Valid CriarPedidoEntradaDTO dados, UriComponentsBuilder uriBuilder) {

		var novoPedido = new Pedido(dados);

		pedidos.save(novoPedido);

		var uri = uriBuilder.path("/pedido/{id}")
				.buildAndExpand(novoPedido.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(APIResponse
						.criado("Pedido criado e cadastrado no Banco de Dados", new PedidoEntradaDTO(novoPedido)));
	}

	
	@GetMapping
	public ResponseEntity<APIResponse<Page<ListarPedidoEntradaDTO>>> listarPedidos(
			@PageableDefault(size = 10, sort = {"pedido"}) Pageable paginacao) {
		
		var paginas = pedidos
				.findAll(paginacao)
				.map(ListarPedidoEntradaDTO::new);
			
		return ResponseEntity.ok(APIResponse
				.sucesso("Lista de pedidos", paginas));
	}

	@PutMapping @Transactional // DADOS DE ENTRADA
	public ResponseEntity<?> atualizarInformacoes(@RequestBody @Valid AtualizarPedidoEntradaDTO dados) {
		try {
			var pedido = pedidos.getReferenceById(dados.id());
			pedido.atualizarDadosEntrada(dados);
			return ResponseEntity.ok(
					APIResponse
							.sucesso(
									"Dados do pedido foram atualizados com sucesso",
									new PedidoEntradaDTO(pedido)
							)
					);
		} catch (EntityNotFoundException erro) {
			return ResponseEntity.status(404)
					.body(APIResponse
							.erro(404, erro.getMessage())
					);
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<APIResponse<?>> excluir(@PathVariable UUID id) {
		if (!pedidos.existsById(id)) { // Verifica se o ID existe no banco
			return ResponseEntity
					.status(404) // Retorna 404 caso não encontre
					.body(APIResponse
							.erro(404, "Pedido não foi encontrado"));
		}

		pedidos.deleteById(id); // Deleta o usuario

		return ResponseEntity
				.status(204) // Retorna 204 se a exclusão foi bem-sucedida
				.body(APIResponse
						.semConteudo("O pedido e seus dados foram removido com sucesso")
				);
	}

	// DADOS DE SAÍDA

	// IMPLEMENTAR A SAÍDA cadastrarPedidoParaUsuario COM CriarPedidoRespostaDTO
	@GetMapping("/{id}/reposta")
	public ResponseEntity<APIResponse<?>> mostrarPedidoAoUsuario(@PathVariable UUID id) {
		var pedido = pedidos
					.findById(id)
					.orElseThrow(
						() -> new EntityNotFoundException("Esse pedido não consta em nossos dados!")
					);
		
		var resposta = new CriarPedidoRespostaDTO(
			// @NotNull UUID id,
			// @NotNull BigDecimal valor,
			// @NotBlank MetodoPagamento metodoPagamento
			pedido.getId(),
			pedido.getValor(),
			pedido.getMetodoPagamento()
		);
	
		return ResponseEntity.ok(
			APIResponse
				.sucesso("Seu pedido:\n", resposta)
		);
	}

	@GetMapping
	public ResponseEntity<APIResponse<Page<ListarPedidoRespostaDTO>>> listarMeusPedidos(
			@PageableDefault(size = 10, sort = {"pedido"}) Pageable paginacao) {
		
		var paginas = pedidos
				.findAll(paginacao)
				.map(ListarPedidoRespostaDTO::new);
			
		return ResponseEntity.ok(APIResponse
				.sucesso("Lista dos meus pedidos", paginas));
	}

}
