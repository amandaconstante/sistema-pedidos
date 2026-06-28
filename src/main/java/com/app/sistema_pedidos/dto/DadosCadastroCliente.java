package com.app.sistema_pedidos.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DadosCadastroCliente(
        @NotBlank
        String nome,
        @NotBlank @Email
        String email,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNasc,
        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,
        @NotNull @Valid
        DadosEndereco endereco) { }
