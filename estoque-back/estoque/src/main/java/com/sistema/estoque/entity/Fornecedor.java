package com.sistema.estoque.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "Fornecedor")
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    
    @Column(unique = true)
    private String email;
    
    private String senha;
    private String telefone;
    private String endereco;
    
    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Produto> produtos = new ArrayList<>();
    
    @OneToMany(mappedBy = "fornecedor", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Movimentacao> movimentacoes = new ArrayList<>();
}