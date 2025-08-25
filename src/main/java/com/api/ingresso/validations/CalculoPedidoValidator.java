package com.api.ingresso.validations;
import java.math.BigDecimal;
import com.api.ingresso.domain.entities.Pedido;
import com.api.ingresso.domain.entities.Item;

public class CalculoPedidoValidator {

    private CalculoPedidoValidator() {}

    public static void calcularValorTotal(Pedido pedido) {
        if (pedido.getItens() == null || pedido.getItens().isEmpty()) {
            pedido.setValor(BigDecimal.ZERO);
            return;
        }

        BigDecimal total = BigDecimal.ZERO;
        for (Item item : pedido.getItens()) {
            if (item.getProduto() != null && item.getProduto().getPreco() != null) {
                BigDecimal subtotal = item.getProduto().getPreco()
                                        .multiply(BigDecimal.valueOf(item.getQuantidade()));
                total = total.add(subtotal);
            }
        }
        pedido.setValor(total);
    }
}
