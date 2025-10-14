package com.api.ingresso.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.api.ingresso.repository.AdminUserRepository;
import com.api.ingresso.repository.UsuarioRepository;
import com.api.ingresso.dto.AuthDTO;

@Service
public class AutenticacaoService implements UserDetailsService {
    private final AdminUserRepository adminUserRepository;

    public AutenticacaoService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminUserRepository.findByLogin(username);
    }
}