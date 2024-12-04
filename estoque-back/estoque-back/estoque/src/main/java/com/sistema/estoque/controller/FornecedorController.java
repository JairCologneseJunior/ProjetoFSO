package com.sistema.estoque.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.estoque.dto.FornecedorCadastroDTO;
import com.sistema.estoque.dto.FornecedorDTO;
import com.sistema.estoque.dto.FornecedorLoginDTO;
import com.sistema.estoque.service.FornecedorService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(
	    origins = "http://localhost:4200",
    allowedHeaders = "*",
    methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping("/api/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {
    private final FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<FornecedorDTO>> getAllFornecedores() {
        return ResponseEntity.ok(fornecedorService.getAllFornecedores());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> getFornecedorById(@PathVariable Long id) {
        FornecedorDTO fornecedor = fornecedorService.getFornecedorById(id);
        return fornecedor != null ? 
            ResponseEntity.ok(fornecedor) : 
            ResponseEntity.notFound().build();
    }

    @PostMapping("/cadastro")
    public ResponseEntity<FornecedorDTO> cadastrar(@RequestBody FornecedorCadastroDTO cadastroDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(fornecedorService.criarFornecedor(cadastroDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<FornecedorDTO> login(@RequestBody FornecedorLoginDTO loginDTO) {
        return ResponseEntity.ok(fornecedorService.autenticar(loginDTO));
    }


    @PutMapping("/{id}")
    public ResponseEntity<FornecedorDTO> atualizarFornecedor(
            @PathVariable Long id,
            @RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorAtualizado = fornecedorService.atualizarFornecedor(id, fornecedorDTO);
        return fornecedorAtualizado != null ? 
            ResponseEntity.ok(fornecedorAtualizado) : 
            ResponseEntity.notFound().build();
    }
    
    @PutMapping("/{id}/perfil")
    public ResponseEntity<FornecedorDTO> atualizarPerfil(
        @PathVariable Long id,
        @RequestBody FornecedorDTO fornecedorDTO) {
        FornecedorDTO fornecedorAtualizado = fornecedorService.atualizarPerfil(id, fornecedorDTO);
        return fornecedorAtualizado != null ? 
            ResponseEntity.ok(fornecedorAtualizado) : 
            ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        if (fornecedorService.getFornecedorById(id) != null) {
            fornecedorService.deletarFornecedor(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}