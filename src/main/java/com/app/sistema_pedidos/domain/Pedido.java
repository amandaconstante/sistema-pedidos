package com.app.sistema_pedidos.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valorTotal;
    private LocalDateTime dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(Cliente cliente) {
        this.valorTotal = BigDecimal.ZERO;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.AGUARDANDO_PAGAMENTO;
        this.cliente = cliente;
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.valorTotal = this.valorTotal.add(item.getPrecoUnitario().multiply(new BigDecimal(item.getQuantidade())));
    }

    public void cancelar() {
        this.status = StatusPedido.CANCELADO;
    }
}
