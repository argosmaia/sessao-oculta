package com.api.ingresso.controller;

import com.api.ingresso.dto.criar.CriarPedidoEntradaDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.PedidoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import com.api.ingresso.dto.atualizar.AtualizarPedidoEntradaDTO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<?>> criarPedido(
            @Valid @RequestBody CriarPedidoEntradaDTO dto) {
        return ResponseEntity.status(201).body(pedidoService.criar(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<?>> buscarPedidoPorId(
            @PathVariable @NotNull UUID id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<APIResponse<?>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<?>> atualizarPedido(
            @PathVariable @NotNull UUID id,
            @Valid @RequestBody AtualizarPedidoEntradaDTO dto) {
        return ResponseEntity.ok(pedidoService.atualizar(id, dto));
    }
}

