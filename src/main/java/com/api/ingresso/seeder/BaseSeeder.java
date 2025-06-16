package com.api.ingresso.seeder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@AllArgsConstructor
@NoArgsConstructor
public class BaseSeeder {

    @Autowired private UsuarioSeeder usuario;

    @Bean
    CommandLineRunner seedAll() {
        return args -> {
            usuario.run();
            System.out.println("🚀 Todos os seeders foram executados com sucesso.");
        };
    }
}