package com.sistema.estoque.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.estoque.dto.ProdutoDTO;
import com.sistema.estoque.entity.Produto;
import com.sistema.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarTodos() {
        return produtoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .map(this::toDTO);
    }

    public ProdutoDTO salvar(ProdutoDTO produtoDTO) {
        Produto produto = toModel(produtoDTO);
        // Calcula a margem de lucro antes de salvar
        produto.setMargemLucro(calcularMargemLucro(produto.getCusto(), produto.getPreco()));
        Produto savedProduto = produtoRepository.save(produto);
        return toDTO(savedProduto);
    }

    public Optional<ProdutoDTO> atualizar(Long id, ProdutoDTO produtoDTO) {
        if (produtoRepository.existsById(id)) {
            Produto produto = toModel(produtoDTO);
            produto.setId(id); // Preserve o ID ao atualizar

            // Calcula a margem de lucro antes de atualizar
            produto.setMargemLucro(calcularMargemLucro(produto.getCusto(), produto.getPreco()));
            Produto updatedProduto = produtoRepository.save(produto);
            return Optional.of(toDTO(updatedProduto));
        }
        return Optional.empty();
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    
    private Double calcularMargemLucro(Double custo, Double preco) {
        if (preco != null && custo != null && preco > 0) {
            return ((preco - custo) / preco) * 100;
        }
        return 0.0; // Retorna 0% se o preço ou custo forem inválidos
    }
  
    private ProdutoDTO toDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(), produto.getNome(),
        		produto.getDescricao(), produto.getQuantidade(),
        		produto.getAtualizacao(),
        		produto.getValidade(),
        		produto.getCusto(),produto.getPreco(),produto.getMargemLucro());
    }


    private Produto toModel(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();
        produto.setId(produtoDTO.id());
        produto.setNome(produtoDTO.nome());
        produto.setDescricao(produtoDTO.descricao());
        produto.setQuantidade(produtoDTO.quantidade());
        produto.setAtualizacao(produtoDTO.atualizacao());
        produto.setValidade(produtoDTO.validade());
        produto.setCusto(produtoDTO.custo());
        produto.setPreco(produtoDTO.preco());
        produto.setMargemLucro(produtoDTO.margemLucro());
        return produto;
    }
}
