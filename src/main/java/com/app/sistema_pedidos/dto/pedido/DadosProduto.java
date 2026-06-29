package com.app.sistema_pedidos.dto.pedido;

import com.app.sistema_pedidos.domain.Produto;

import java.math.BigDecimal;

public record DadosProduto(Long id, String nome, String descricao, BigDecimal preco) {
    public DadosProduto(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco());
    }
}
