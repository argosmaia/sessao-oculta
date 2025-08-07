package com.api.ingresso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.dto.PedidoEntradaDTO;
import com.api.ingresso.repository.PedidoRepository;
import com.api.ingresso.response.APIResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidos;

    public PedidoController(PedidoRepository pedidos) {
        this.pedidos = pedidos;
    }

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
}
