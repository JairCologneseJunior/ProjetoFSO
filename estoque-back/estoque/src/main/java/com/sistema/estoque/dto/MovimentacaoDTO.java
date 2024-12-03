package com.sistema.estoque.dto;

import java.time.LocalDateTime;

public record MovimentacaoDTO(Long id, Long produtoId, int quantidadeAlterada, LocalDateTime dataMovimentacao, String tipoMovimentacao, String nomeProduto) {}

