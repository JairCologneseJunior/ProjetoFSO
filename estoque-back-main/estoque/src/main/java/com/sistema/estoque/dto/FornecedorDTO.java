package com.sistema.estoque.dto;

public record FornecedorDTO(
        Long id,
        String nome,
        String email,
        String telefone,
        String endereco
) {
}