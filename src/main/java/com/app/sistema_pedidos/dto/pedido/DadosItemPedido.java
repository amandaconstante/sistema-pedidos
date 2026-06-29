package com.app.sistema_pedidos.dto.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DadosItemPedido(
        @NotNull
        Long produtoId,
        @Positive
        int quantidade
) {

}
