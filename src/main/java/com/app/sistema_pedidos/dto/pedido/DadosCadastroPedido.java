package com.app.sistema_pedidos.dto.pedido;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosCadastroPedido(
        @NotNull
        Long clienteId,
        List<DadosItemPedido> itens
) {
}
