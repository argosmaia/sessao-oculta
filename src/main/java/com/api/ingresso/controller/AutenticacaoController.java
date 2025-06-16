package com.api.ingresso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.ingresso.domain.entities.AdminUser;
import com.api.ingresso.dto.AuthDTO;
import com.api.ingresso.dto.TokenJWTDTO;
import com.api.ingresso.response.APIResponse;
import com.api.ingresso.service.JwtTokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired private AuthenticationManager manager;
    @Autowired private JwtTokenService tokens;

    @PostMapping public ResponseEntity<APIResponse<TokenJWTDTO>> efetuarLogin(@RequestBody @Valid AuthDTO dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(token);
        var tokenJWT = tokens.gerarToken((AdminUser) authentication.getPrincipal());
        var resposta = APIResponse.sucesso("Login realizado com sucesso", new TokenJWTDTO(tokenJWT));
        return ResponseEntity.ok(resposta);
    }
}
 