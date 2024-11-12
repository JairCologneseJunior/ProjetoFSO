package com.sistema.estoque.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.sistema.estoque.entity.Produto;

public record ProdutoDTO(long id, String nome, String descricao,
		int quantidade, LocalDateTime atualizacao,  LocalDate validade, Double custo, Double preco, Double margemLucro) {
    
    public static ProdutoDTO fromModel(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(), produto.getDescricao(),
        		produto.getQuantidade(), produto.getAtualizacao(), produto.getValidade(),
        		produto.getCusto(),produto.getPreco(),produto.getMargemLucro());
    }
}
