package com.app.sistema_pedidos.dto;

import com.app.sistema_pedidos.domain.Endereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank @Pattern(regexp = "\\d{8}", message = "Formato do CEP é inválido")
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        String complemento,
        String numero )
{
    public DadosEndereco (Endereco endereco) {
        this(endereco.getLogradouro(), endereco.getBairro(), endereco.getCep(), endereco.getCidade(),
                endereco.getUf(), endereco.getComplemento(), endereco.getNumero());
    }
}
