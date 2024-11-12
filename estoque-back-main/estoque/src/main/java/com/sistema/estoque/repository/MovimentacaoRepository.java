package com.sistema.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.estoque.entity.Movimentacao;

public interface MovimentacaoRepository  extends JpaRepository<Movimentacao, Long> {

}
