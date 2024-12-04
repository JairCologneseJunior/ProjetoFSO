package com.sistema.estoque.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.estoque.dto.MovimentacaoDTO;
import com.sistema.estoque.service.FornecedorService;
import com.sistema.estoque.service.MovimentacaoService;

import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/fornecedores/{fornecedorId}/movimentacoes")
@RequiredArgsConstructor
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;
    private final FornecedorService fornecedorService;

    @PostMapping("/registrar")
    public ResponseEntity<MovimentacaoDTO> registrarMovimentacao(
            @PathVariable Long fornecedorId,
            @RequestParam Long produtoId,
            @RequestParam int novaQuantidade) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        MovimentacaoDTO movimentacao = movimentacaoService.registrarMovimentacao(
            fornecedorId, produtoId, novaQuantidade);
        return ResponseEntity.ok(movimentacao);
    }
    
    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> listarMovimentacoes(
            @PathVariable Long fornecedorId) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimentacaoService.listarMovimentacoesPorFornecedor(fornecedorId));
    }
}