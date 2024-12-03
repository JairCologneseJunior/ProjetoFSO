package com.sistema.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.estoque.entity.Fornecedor;
import com.sistema.estoque.entity.Movimentacao;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
	
	 Optional<Fornecedor> findByEmail(String email);
	    boolean existsByEmail(String email);

}

