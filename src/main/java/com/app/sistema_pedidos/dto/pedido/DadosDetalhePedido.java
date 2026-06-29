package com.app.sistema_pedidos.dto.pedido;

import com.app.sistema_pedidos.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosDetalhePedido(
        Long id,
        String nomeCliente,
        BigDecimal valorTotal,
        String status,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime dataCriacao,
        List<DadosDetalheItem> itens
) {
    public DadosDetalhePedido(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getCliente().getNome(),
                pedido.getValorTotal(),
                pedido.getStatus().name(),
                pedido.getDataCriacao(),
                pedido.getItens().stream().map(DadosDetalheItem::new).toList()
        );
    }
}
