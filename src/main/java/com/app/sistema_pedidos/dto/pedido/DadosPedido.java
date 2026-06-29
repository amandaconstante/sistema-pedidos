package com.app.sistema_pedidos.dto.pedido;

import com.app.sistema_pedidos.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPedido (
        Long id,
        Long clienteId,
        BigDecimal valorTotal,
        String status,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataCriacao
) {
    public DadosPedido(Pedido pedido) {
        this(pedido.getId(), pedido.getCliente().getId(), pedido.getValorTotal(), pedido.getStatus().name(), pedido.getDataCriacao());
    }
}
