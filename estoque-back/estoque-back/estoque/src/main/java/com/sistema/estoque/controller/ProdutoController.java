package com.sistema.estoque.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.estoque.dto.ProdutoDTO;
import com.sistema.estoque.entity.Produto;
import com.sistema.estoque.repository.ProdutoRepository;
import com.sistema.estoque.service.FornecedorService;
import com.sistema.estoque.service.ProdutoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@CrossOrigin
@RestController
@RequestMapping("/api/fornecedores/{fornecedorId}/produtos")
@RequiredArgsConstructor
public class ProdutoController {
    private final ProdutoService produtoService;
    private final FornecedorService fornecedorService;
    private final ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos(@PathVariable Long fornecedorId) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoService.listarTodosPorFornecedor(fornecedorId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> buscarPorId(
            @PathVariable Long fornecedorId,
            @PathVariable Long id) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        return produtoService.buscarPorId(fornecedorId, id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> salvar(
            @PathVariable Long fornecedorId,
            @RequestBody ProdutoDTO produtoDTO) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(produtoService.salvar(fornecedorId, produtoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDTO> atualizar(
            @PathVariable Long fornecedorId,
            @PathVariable Long id,
            @RequestBody ProdutoDTO produtoDTO) {
        if (!fornecedorService.validarFornecedor(fornecedorId)) {
            return ResponseEntity.notFound().build();
        }
        return produtoService.atualizar(fornecedorId, id, produtoDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        try {
            Optional<Produto> produtoOpt = produtoRepository.findById(id);
            
            if (produtoOpt.isPresent()) {
                Produto produto = produtoOpt.get();
                // Remove a referência do produto no fornecedor
                produto.getFornecedor().getProdutos().remove(produto);
                // Remove a referência do fornecedor no produto
                produto.setFornecedor(null);
                produtoRepository.delete(produto);
                return ResponseEntity.ok().build();
            }
            
            return ResponseEntity.notFound().build();
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}