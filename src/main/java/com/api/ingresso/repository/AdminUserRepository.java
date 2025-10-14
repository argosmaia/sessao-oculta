package com.api.ingresso.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.ingresso.domain.entities.AdminUser;

import java.util.Optional;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long>{
    @Override
    Optional<AdminUser> findById(Long aLong);
    UserDetails findByLogin(String login);
}
