package com.sistema.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.estoque.entity.Movimentacao;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, Long> {

	  List<Movimentacao> findByFornecedorId(Long fornecedorId);

}
