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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.dto.PedidoEntradaDTO;
import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.listar.ListarPedidoEntradaDTO;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.PedidoService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;
    private final PedidoService pedidoService;

    public PedidoController(PedidoRepository pedidos, PedidoService pedidoService) {
        this.pedidos = pedidos;
        this.pedidoService = pedidoService;
    }

    @PostMapping @Transactional
	public ResponseEntity<APIResponse<?>> cadastrarUsuario(@RequestBody @Valid CriarPedidoEntradaDTO dados, UriComponentsBuilder uriBuilder) {

		var pedido = new Pedido(dados);

		pedidos.save(pedido);

		var uri = uriBuilder.path("/pedido/{id}")
				.buildAndExpand(pedido.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(APIResponse
						.criado("Pedido dado entrada", new PedidoEntradaDTO(pedido)));
	}

    @GetMapping
    public ResponseEntity<APIResponse<Page<ListarPedidoEntradaDTO>>> listarPedidos(
        @PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {

        var pagina = pedidos.findAll(paginacao)
                        .map(ListarPedidoEntradaDTO::new);

        return ResponseEntity.ok(
                APIResponse.sucesso("Lista de pedidos", pagina)
        );
    }


	// Listar por nomes
	@GetMapping("/buscar/{id}")
	public ResponseEntity<APIResponse<UsuarioDTO>> buscarPorNome(@RequestParam String nome) {
		return ResponseEntity.ok(pedidoService.buscar(nome));
	}


	@PutMapping @Transactional
	public ResponseEntity<?> atualizarInformacoes(@RequestBody @Valid AtualizarDadosUsuarioDTO dados) {
		try {
			var pedido = pedidos.getReferenceById(dados.id());
			pedidos.atualizarDados(dados);
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
}


