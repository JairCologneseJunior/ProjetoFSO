package com.sistema.estoque.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "Produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private int quantidade;
    private LocalDateTime atualizacao;
    private LocalDate validade;
    private Double custo;
    private Double preco;
    private Double margemLucro;

}
