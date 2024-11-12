package com.sistema.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.estoque.dto.MovimentacaoDTO;
import com.sistema.estoque.service.MovimentacaoService;

@CrossOrigin
@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping("/registrar-saida")
    public ResponseEntity<MovimentacaoDTO> registrarSaida(@RequestParam Long produtoId, @RequestParam int novaQuantidade) {
        MovimentacaoDTO movimentacaoDTO = movimentacaoService.registrarMovimentacao(produtoId, novaQuantidade);
        return ResponseEntity.ok(movimentacaoDTO);
    }
    
    @GetMapping("/listar-todas")
    public ResponseEntity<List<MovimentacaoDTO>> listarTodas() {
        List<MovimentacaoDTO> movimentacoes = movimentacaoService.listarTodasMovimentacoes();
        return ResponseEntity.ok(movimentacoes);
    }
}