package com.app.sistema_pedidos.dto.cliente;

import com.app.sistema_pedidos.domain.Cliente;

public record DadosCliente(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DadosCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf());
    }
}
