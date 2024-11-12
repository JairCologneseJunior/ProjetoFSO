package com.sistema.estoque.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.estoque.dto.MovimentacaoDTO;
import com.sistema.estoque.entity.Movimentacao;
import com.sistema.estoque.entity.Produto;
import com.sistema.estoque.repository.MovimentacaoRepository;
import com.sistema.estoque.repository.ProdutoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public MovimentacaoDTO registrarMovimentacao(Long produtoId, int novaQuantidade) {
        Produto produto = produtoRepository.findById(produtoId)
            .orElseThrow(() -> new IllegalArgumentException("Produto nao encontrado"));

        int diferenca = novaQuantidade - produto.getQuantidade();
        String tipoMovimentacao = diferenca < 0 ? "SAIDA" : "ENTRADA";
       
        if (diferenca != 0) {
            int quantidadeAlterada = Math.abs(diferenca);
            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setProduto(produto);
            movimentacao.setQuantidadeAlterada(quantidadeAlterada);
            movimentacao.setDataMovimentacao(LocalDateTime.now());
            movimentacao.setTipoMovimentacao(tipoMovimentacao);
            movimentacao.setNomeProduto(produto.getNome());

            // Atualiza a quantidade do produto
            produto.setQuantidade(novaQuantidade);
            produto.setAtualizacao(LocalDateTime.now());
            produtoRepository.save(produto);

            movimentacaoRepository.save(movimentacao);

            return new MovimentacaoDTO(movimentacao.getId(),
                                       produto.getId(),
                                       movimentacao.getQuantidadeAlterada(),
                                       movimentacao.getDataMovimentacao(),
                                       movimentacao.getTipoMovimentacao(),
                                       movimentacao.getNomeProduto());
        } else {
            throw new IllegalArgumentException("A quantidade informada nao representa uma movimentacao.");
        }
    }

    
    public List<MovimentacaoDTO> listarTodasMovimentacoes() {
        return movimentacaoRepository.findAll().stream()
            .map(mov -> new MovimentacaoDTO(mov.getId(), 
                                            mov.getProduto().getId(), 
                                            mov.getQuantidadeAlterada(), 
                                            mov.getDataMovimentacao(), 
                                            mov.getTipoMovimentacao(),
                                            mov.getNomeProduto()))
            .collect(Collectors.toList());
    }
}