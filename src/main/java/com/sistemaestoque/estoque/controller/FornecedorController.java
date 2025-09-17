package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.model.Fornecedor;
import com.sistemaestoque.estoque.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public List<Fornecedor> listarFornecedores() {
        return fornecedorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
        return fornecedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Fornecedor> cadastrarFornecedor(@RequestBody Fornecedor fornecedor) {
        Fornecedor novoFornecedor = fornecedorService.salvarFornecedor(fornecedor);
        return ResponseEntity.ok(novoFornecedor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Fornecedor> atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedorDetalhes) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
        if (fornecedor.isPresent()) {
            Fornecedor fornecedorAtualizado = fornecedor.get();
            fornecedorAtualizado.setNome(fornecedorDetalhes.getNome());
            fornecedorAtualizado.setContato(fornecedorDetalhes.getContato());
            fornecedorAtualizado.setTelefone(fornecedorDetalhes.getTelefone());
            fornecedorAtualizado.setEmail(fornecedorDetalhes.getEmail());
            return ResponseEntity.ok(fornecedorService.salvarFornecedor(fornecedorAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFornecedor(@PathVariable Long id) {
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);
        if (fornecedor.isPresent()) {
            fornecedorService.deletarFornecedor(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}