package com.sistema.estoque.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sistema.estoque.dto.ProdutoDTO;
import com.sistema.estoque.entity.Fornecedor;
import com.sistema.estoque.entity.Produto;
import com.sistema.estoque.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	 private final ProdutoRepository produtoRepository;
	 private final FornecedorService fornecedorService;

    public List<ProdutoDTO> listarTodosPorFornecedor(Long fornecedorId) {
        return produtoRepository.findByFornecedorId(fornecedorId).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutoDTO> buscarPorId(Long fornecedorId, Long produtoId) {
        return produtoRepository.findByIdAndFornecedorId(produtoId, fornecedorId)
                .map(this::toDTO);
    }

    @Transactional
    public ProdutoDTO salvar(Long fornecedorId, ProdutoDTO produtoDTO) {
        Fornecedor fornecedor = fornecedorService.getFornecedorEntityById(fornecedorId);
        if (fornecedor == null) {
            throw new RuntimeException("Fornecedor não encontrado");
        }

        Produto produto = toModel(produtoDTO);
        produto.setFornecedor(fornecedor);
        produto.setMargemLucro(calcularMargemLucro(produto.getCusto(), produto.getPreco()));
        return toDTO(produtoRepository.save(produto));
    }

    @Transactional
    public Optional<ProdutoDTO> atualizar(Long fornecedorId, Long produtoId, ProdutoDTO produtoDTO) {
        return produtoRepository.findByIdAndFornecedorId(produtoId, fornecedorId)
            .map(produto -> {
                Produto produtoAtualizado = toModel(produtoDTO);
                produtoAtualizado.setId(produtoId);
                produtoAtualizado.setFornecedor(produto.getFornecedor());
                produtoAtualizado.setMargemLucro(calcularMargemLucro(produtoAtualizado.getCusto(), 
                    produtoAtualizado.getPreco()));
                return toDTO(produtoRepository.save(produtoAtualizado));
            });
    }
    public boolean deletar(Long id) {
        try {
            produtoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
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
