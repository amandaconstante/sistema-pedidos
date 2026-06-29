package com.app.sistema_pedidos.dto.cliente;

import com.app.sistema_pedidos.domain.Cliente;

import java.time.LocalDate;

public record DadosResponseCliente(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNasc,
        DadosEndereco endereco
) {
    public DadosResponseCliente(Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf(), cliente.getDataNasc(), new DadosEndereco(cliente.getEndereco()));
    }
}
