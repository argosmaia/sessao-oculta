package com.api.ingresso.factory;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.api.ingresso.domain.embeddable.Bebida;
import com.api.ingresso.domain.embeddable.Lanche;
import com.api.ingresso.dto.criar.CriarProdutoDTO;
import com.github.javafaker.Faker;

@Component
public class ProdutoFactory {

    private final Faker faker = new Faker(new Locale("pt-BR"));
    private final LancheFactory lancheFactory;
    private final BebidaFactory bebidaFactory;

    public ProdutoFactory(LancheFactory lancheFactory, BebidaFactory bebidaFactory) {
        this.lancheFactory = lancheFactory;
        this.bebidaFactory = bebidaFactory;
    }

    public CriarProdutoDTO criarProdutoDTOAleatorio() {
        Lanche lanche = lancheFactory.criarLancheAleatorio();
        Bebida bebida = bebidaFactory.criarBebidaAleatoria();

        String nome = lanche + " + " + bebida;
        String descricao = "Combo de " + lanche + " com " + bebida + ", delicioso e fresco!";
        BigDecimal preco = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(10.0, 50.0))
                .setScale(2, BigDecimal.ROUND_HALF_UP);

        return new CriarProdutoDTO(nome, descricao, lanche, bebida, preco);
    }
}
