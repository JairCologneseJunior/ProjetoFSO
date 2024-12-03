package com.sistema.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sistema.estoque.dto.FornecedorCadastroDTO;
import com.sistema.estoque.dto.FornecedorDTO;
import com.sistema.estoque.dto.FornecedorLoginDTO;
import com.sistema.estoque.entity.Fornecedor;
import com.sistema.estoque.repository.FornecedorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FornecedorService {
    private final FornecedorRepository fornecedorRepository;
    private final PasswordEncoder passwordEncoder;


    public boolean validarFornecedor(Long fornecedorId) {
        return fornecedorRepository.existsById(fornecedorId);
    }

    public List<FornecedorDTO> getAllFornecedores() {
        return fornecedorRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public FornecedorDTO getFornecedorById(Long id) {
        return fornecedorRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }
    
    public Fornecedor getFornecedorEntityById(Long id) {
        return fornecedorRepository.findById(id)
                .orElse(null);
    }

    @Transactional
    public FornecedorDTO criarFornecedor(FornecedorCadastroDTO dto) {
        if (fornecedorRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email já cadastrado");
        }
        
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setSenha(passwordEncoder.encode(dto.senha()));
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        
        return toDTO(fornecedorRepository.save(fornecedor));
    }

    public FornecedorDTO autenticar(FornecedorLoginDTO loginDTO) {
        return fornecedorRepository.findByEmail(loginDTO.email())
            .filter(f -> passwordEncoder.matches(loginDTO.senha(), f.getSenha()))
            .map(this::toDTO)
            .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));
    }
    
    

    @Transactional
    public FornecedorDTO atualizarFornecedor(Long id, FornecedorDTO fornecedorDTO) {
        return fornecedorRepository.findById(id)
            .map(fornecedor -> {
                fornecedor.setNome(fornecedorDTO.nome());
                fornecedor.setEmail(fornecedorDTO.email());
                fornecedor.setTelefone(fornecedorDTO.telefone());
                fornecedor.setEndereco(fornecedorDTO.endereco());
                return toDTO(fornecedorRepository.save(fornecedor));
            })
            .orElse(null);
    }
    
    @Transactional
    public FornecedorDTO atualizarPerfil(Long id, FornecedorDTO dto) {
        return fornecedorRepository.findById(id)
            .map(fornecedor -> {
                fornecedor.setNome(dto.nome());
                fornecedor.setEmail(dto.email());
                fornecedor.setTelefone(dto.telefone());
                fornecedor.setEndereco(dto.endereco());
                return toDTO(fornecedorRepository.save(fornecedor));
            })
            .orElse(null);
    }

    @Transactional
    public void deletarFornecedor(Long id) {
        fornecedorRepository.deleteById(id);
    }

    private FornecedorDTO toDTO(Fornecedor fornecedor) {
        return new FornecedorDTO(
            fornecedor.getId(),
            fornecedor.getNome(),
            fornecedor.getEmail(),
            fornecedor.getTelefone(),
            fornecedor.getEndereco()
        );
    }

    private Fornecedor toEntity(FornecedorDTO dto) {
        Fornecedor fornecedor = new Fornecedor();
        fornecedor.setId(dto.id());
        fornecedor.setNome(dto.nome());
        fornecedor.setEmail(dto.email());
        fornecedor.setTelefone(dto.telefone());
        fornecedor.setEndereco(dto.endereco());
        return fornecedor;
    }
}