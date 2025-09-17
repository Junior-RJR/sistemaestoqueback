package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.model.Cotacao;
import com.sistemaestoque.estoque.service.CotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cotacoes")
public class CotacaoController {

    @Autowired
    private CotacaoService cotacaoService;

    @GetMapping
    public List<Cotacao> listarCotacoes() {
        return cotacaoService.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Cotacao> registrarCotacao(@RequestParam Long produtoId, @RequestParam Long fornecedorId, @RequestParam double preco) {
        try {
            Cotacao novaCotacao = cotacaoService.salvarCotacao(produtoId, fornecedorId, preco);
            return ResponseEntity.ok(novaCotacao);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}