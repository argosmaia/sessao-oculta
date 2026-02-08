package com.api.ingresso.seeder;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BaseSeeder {

    private final UsuarioSeeder usuario;
    private final FilmeSeeder filme;
    private final BebidaSeeder bebida;
    private final LancheSeeder lanche;
    private final ProdutoSeeder produto;
    private final SalaSeeder sala;
    private final SessaoSeeder sessao;

    public BaseSeeder(
        UsuarioSeeder usuario,
        FilmeSeeder filme,
        BebidaSeeder bebida,
        LancheSeeder lanche,
        ProdutoSeeder produto,
        SalaSeeder sala,
        SessaoSeeder sessao
    ) {
        this.usuario = usuario;
        this.filme = filme;
        this.bebida = bebida;
        this.lanche = lanche;
        this.produto = produto;
        this.sala = sala;
        this.sessao = sessao;
    }

    @Bean
    public CommandLineRunner seedAll() {
        return args -> {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Quer popular o banco? (sim/não)");
            String resposta = scanner.nextLine().trim().toLowerCase();

            if (resposta.equals("sim") || resposta.equals("s")) {
                System.out.println("🚀 Populando o banco...");
                usuario.run();
                filme.run();
                lanche.run();
                bebida.run();
                produto.run();
                sala.run();
                sessao.run();
                System.out.println("✅ Todos os seeders foram executados com sucesso.");
            } else {
                System.out.println("⏭️ Seeders ignorados. Continuando execução...");
            }

            // scanner.close(); // opcional, mas fecha o scanner
        };
    }
}
