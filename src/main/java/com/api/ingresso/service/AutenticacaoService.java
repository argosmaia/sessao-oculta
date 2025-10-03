package com.api.ingresso.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.ingresso.domain.entities.Usuario;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.dto.AuthDTO;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService tokenService;

    public AutenticacaoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtTokenService tokenService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public AuthDTO registrar(RegistrarDTO dto) {
        if(usuarioRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado");
        }

        var usuario = new Usuario(
            dto.nome(),
            dto.email(),
            passwordEncoder.encode(dto.senha()),
            Role.USER
        );

        usuarioRepository.save(usuario);

        var token = tokenService.generateToken(usuario.getEmail(), usuario.getRole().name());
        return new AuthResponseDTO(token);
    }

    public AuthDTO login(LoginDTO dto) {
        var usuario = usuarioRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if(!passwordEncoder.matches(dto.senha(), usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        var token = tokenService.generateToken(usuario.getEmail(), usuario.getRole().name());
            return new AuthResponseDTO(token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username);
    }
}