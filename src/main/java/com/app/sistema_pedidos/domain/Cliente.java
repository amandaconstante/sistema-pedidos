package com.app.sistema_pedidos.domain;

import com.app.sistema_pedidos.dto.cliente.DadosCadastroCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private LocalDate dataNasc;
    @Embedded
    private Endereco endereco;

    public Cliente(String nome, String email, String cpf, LocalDate dataNasc, Endereco endereco) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNasc = dataNasc;
        this.endereco = endereco;
    }

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.dataNasc = dados.dataNasc();
        this.endereco = new Endereco(dados.endereco());
    }
}
