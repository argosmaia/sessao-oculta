package com.api.ingresso.service;

import com.api.ingresso.domain.entities.AdminUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtTokenService {
    @Value("${api.security.token.secret}")
    private String nossoSegredinho;

    public String gerarToken(AdminUser adminUser) {
        try {
            var algoritmo = Algorithm.HMAC256(nossoSegredinho);
            return JWT.create()
                    .withIssuer("Ingresso")
                    .withSubject(adminUser.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public Instant dataExpiracao() {
        return LocalDateTime.now().plusDays(30).toInstant(ZoneOffset.of("-03:00"));
    }
}
