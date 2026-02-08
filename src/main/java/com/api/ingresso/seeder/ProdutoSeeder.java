package com.api.ingresso.seeder;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.entities.Produto;
import com.api.ingresso.factory.ProdutoFactory;
import com.api.ingresso.repository.ProdutoRepository;

@Component
public class ProdutoSeeder {

    private final ProdutoRepository produtoRepository;
    private final ProdutoFactory produtoFactory;

    public ProdutoSeeder(ProdutoRepository produtoRepository, ProdutoFactory produtoFactory) {
        this.produtoRepository = produtoRepository;
        this.produtoFactory = produtoFactory;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            var dto = produtoFactory.criarProdutoDTOAleatorio();
            produtoRepository.save(new Produto(dto));
        }
        System.out.println("✅ 10 produtos foram inseridos no banco.");
    }
}
