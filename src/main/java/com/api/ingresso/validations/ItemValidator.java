package com.api.ingresso.validations;

import java.math.BigDecimal;
import java.util.List;
import com.api.ingresso.dto.ItemEntradaDTO;

public class ItemValidator {

    private ItemValidator() {} // Classe utilitária, não precisa instanciar

    public static void validarItens(List<ItemEntradaDTO> itens) {
        if (itens == null || itens.isEmpty()) 
            throw new IllegalArgumentException("Lista de itens vazia");

        for (ItemEntradaDTO item : itens) {
            if (item == null) throw new IllegalArgumentException("Item nulo na lista");
            if (item.quantidade() < 1) 
                throw new IllegalArgumentException("Quantidade do item não pode ser zero ou negativa.");
            if (item.preco().compareTo(BigDecimal.ONE) < 0)
                throw new IllegalArgumentException("Preço precisa ter um valor de pelo menos R$ 1.");
        }
    }
}
