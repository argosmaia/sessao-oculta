package com.api.ingresso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.ingresso.domain.entities.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{
    UserDetails findByLogin(String login);
}
