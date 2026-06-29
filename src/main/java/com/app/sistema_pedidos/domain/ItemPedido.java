package com.app.sistema_pedidos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "itens_pedido")
// Linha NF, como: "2 unidades (quantidade) do Produto X, custando R$ 50,00 cada (precoUnitario)"
public class ItemPedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    private int quantidade;
    private BigDecimal precoUnitario;

    public ItemPedido(Produto produto, Pedido pedido, int quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.pedido = pedido;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

}
