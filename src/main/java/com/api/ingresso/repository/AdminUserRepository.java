package com.api.ingresso.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.ingresso.entities.model.AdminUser;

public interface AdminUserRepository extends JpaRepository<AdminUser, UUID>{
    UserDetails findByLogin(String login);
}
