package com.sistema.estoque.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.estoque.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	 List<Produto> findByFornecedorId(Long fornecedorId);
	 Optional<Produto> findByIdAndFornecedorId(Long id, Long fornecedorId);
}