package com.api.ingresso.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.ingresso.repository.AdminUserRepository;
import com.api.ingresso.service.JwtTokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired private JwtTokenService token;
    @Autowired private AdminUserRepository adminUser;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException  {
        var tokenJWT = recuperarToken(request);

        if(tokenJWT != null) {
            var objeto = token.getSubject(tokenJWT);
            var admin = adminUser.findByLogin(objeto);

            var authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

        System.out.println(tokenJWT);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        
        if(authorizationHeader == null) {
            throw new RuntimeException("Token não foi enviado no cabeçalho Authorization!");
        }
        return authorizationHeader.replace("Bearer", "");
    }
}
