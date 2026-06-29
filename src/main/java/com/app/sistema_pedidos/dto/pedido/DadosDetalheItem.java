package com.app.sistema_pedidos.dto.pedido;

import com.app.sistema_pedidos.domain.ItemPedido;

import java.math.BigDecimal;

public record DadosDetalheItem(
        Long produtoId,
        String nomeProduto,
        int quantidade,
        BigDecimal precoUnitario,
        BigDecimal subtotal
) {
    public DadosDetalheItem(ItemPedido item) {
        this(
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitario(),
                item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade()))
        );
    }
}