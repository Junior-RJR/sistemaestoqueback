package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.dto.QuantidadeRequest;
import com.sistemaestoque.estoque.model.Produto;
import com.sistemaestoque.estoque.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueService estoqueService;

    @PostMapping("/entrada/{produtoId}")
    public ResponseEntity<Produto> registrarEntrada(@PathVariable Long produtoId, @RequestBody QuantidadeRequest quantidadeRequest) {
        try {
            Produto produtoAtualizado = estoqueService.registrarEntrada(produtoId, quantidadeRequest.getQuantidade());
            if (produtoAtualizado != null) {
                return ResponseEntity.ok(produtoAtualizado);
            }
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/saida/{produtoId}")
    public ResponseEntity<Produto> registrarSaida(@PathVariable Long produtoId, @RequestBody QuantidadeRequest quantidadeRequest) {
        try {
            Produto produtoAtualizado = estoqueService.registrarSaida(produtoId, quantidadeRequest.getQuantidade());
            if (produtoAtualizado != null) {
                return ResponseEntity.ok(produtoAtualizado);
            }
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}