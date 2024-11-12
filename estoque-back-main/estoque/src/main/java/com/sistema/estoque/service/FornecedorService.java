package com.sistema.estoque.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sistema.estoque.entity.Fornecedor;
import com.sistema.estoque.repository.FornecedorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElse(null);
    }

    @Transactional
    public Fornecedor criarFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Transactional
    public Fornecedor atualizarFornecedor(Long id, Fornecedor fornecedorAtualizado) {
        Optional<Fornecedor> fornecedorOpt = fornecedorRepository.findById(id);
        if (fornecedorOpt.isPresent()) {
            Fornecedor fornecedor = fornecedorOpt.get();
            fornecedor.setNome(fornecedorAtualizado.getNome());
            fornecedor.setEmail(fornecedorAtualizado.getEmail());
            fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
            fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
            return fornecedorRepository.save(fornecedor);
        }
        return null;
    }

    @Transactional
    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }
}