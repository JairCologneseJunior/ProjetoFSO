package com.sistema.estoque.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.estoque.dto.MovimentacaoDTO;
import com.sistema.estoque.entity.Movimentacao;
import com.sistema.estoque.entity.Produto;
import com.sistema.estoque.repository.MovimentacaoRepository;
import com.sistema.estoque.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    private final ProdutoRepository produtoRepository;
    private final FornecedorService fornecedorService;

    @Transactional
    public MovimentacaoDTO registrarMovimentacao(Long fornecedorId, Long produtoId, int novaQuantidade) {
        Produto produto = produtoRepository.findByIdAndFornecedorId(produtoId, fornecedorId)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado para este fornecedor"));

        int diferenca = novaQuantidade - produto.getQuantidade();
        String tipoMovimentacao = diferenca < 0 ? "SAIDA" : "ENTRADA";
        
        if (diferenca == 0) {
            throw new IllegalArgumentException("A quantidade informada não representa uma movimentação");
        }

        Movimentacao movimentacao = new Movimentacao();
        movimentacao.setProduto(produto);
        movimentacao.setFornecedor(produto.getFornecedor());
        movimentacao.setQuantidadeAlterada(Math.abs(diferenca));
        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setTipoMovimentacao(tipoMovimentacao);
        movimentacao.setNomeProduto(produto.getNome());

        produto.setQuantidade(novaQuantidade);
        produto.setAtualizacao(LocalDateTime.now());
        produtoRepository.save(produto);

        Movimentacao savedMovimentacao = movimentacaoRepository.save(movimentacao);
        return toDTO(savedMovimentacao);
    }

    public List<MovimentacaoDTO> listarMovimentacoesPorFornecedor(Long fornecedorId) {
        return movimentacaoRepository.findByFornecedorId(fornecedorId).stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    private MovimentacaoDTO toDTO(Movimentacao movimentacao) {
        return new MovimentacaoDTO(
            movimentacao.getId(),
            movimentacao.getProduto().getId(),
            movimentacao.getQuantidadeAlterada(),
            movimentacao.getDataMovimentacao(),
            movimentacao.getTipoMovimentacao(),
            movimentacao.getNomeProduto()
        );
    }
}