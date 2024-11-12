package com.sistema.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.estoque.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}