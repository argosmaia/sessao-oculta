/**
 *
 */
package com.api.ingresso.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.api.ingresso.domain.entities.Usuario;
import com.api.ingresso.dto.UsuarioDTO;
import com.api.ingresso.dto.atualizar.AtualizarDadosUsuarioDTO;
import com.api.ingresso.dto.criar.CriarUsuarioDTO;
import com.api.ingresso.dto.listar.ListarUsuariosDTO;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.UsuarioService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

/**
 *
 */
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired private UsuarioRepository usuarios;
	@Autowired private UsuarioService usuarioService;


	@PostMapping @Transactional
	public ResponseEntity<APIResponse<?>> cadastrarUsuario(@RequestBody @Valid CriarUsuarioDTO dados, UriComponentsBuilder uriBuilder) {

		var usuario = new Usuario(dados);

		usuarios.save(usuario);

		var uri = uriBuilder.path("/usuario/{id}")
				.buildAndExpand(usuario.getId())
				.toUri();

		return ResponseEntity
				.created(uri)
				.body(APIResponse
						.criado("Usuário criado", new UsuarioDTO(usuario)));
	}

	@GetMapping
	public ResponseEntity<APIResponse<Page<ListarUsuariosDTO>>> listarUsuarios(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		var paginas = usuarios.findAll(paginacao)
							.map(ListarUsuariosDTO::new);

		return ResponseEntity.ok(APIResponse
				.sucesso("Lista de Usuários", paginas));
	}

	// Listar por nomes
	@GetMapping("/buscar/{id}")
	public ResponseEntity<APIResponse<UsuarioDTO>> buscarPorNome(@RequestParam String nome) {
		return ResponseEntity.ok(usuarioService.buscar(nome));
	}


	@PutMapping @Transactional
	public ResponseEntity<?> atualizarInformacoes(@RequestBody @Valid AtualizarDadosUsuarioDTO dados) {
		try {
			var usuario = usuarios.getReferenceById(dados.id());
			usuario.atualizarDados(dados);
			return ResponseEntity.ok(
					APIResponse
							.sucesso(
									"Dados do usuário foram atualizados com sucesso",
									new UsuarioDTO(usuario)
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
		if (!usuarios.existsById(id)) { // Verifica se o ID existe no banco
			return ResponseEntity
					.status(404) // Retorna 404 caso não encontre
					.body(APIResponse
							.erro(404, "Usuário não foi encontrado"));
		}

		usuarios.deleteById(id); // Deleta o usuario

		return ResponseEntity
				.status(204) // Retorna 204 se a exclusão foi bem-sucedida
				.body(APIResponse
						.semConteudo("O usuário e seus dados foram removido com sucesso")
				);
	}
}
