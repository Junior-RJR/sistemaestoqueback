package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.model.Produto;
import com.sistemaestoque.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public Produto adicionar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto produtoDetalhes) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            Produto produtoAtualizado = produto.get();
            produtoAtualizado.setNome(produtoDetalhes.getNome());
            produtoAtualizado.setCategoria(produtoDetalhes.getCategoria());
            produtoAtualizado.setDescricao(produtoDetalhes.getDescricao());
            produtoAtualizado.setPreco(produtoDetalhes.getPreco());
            produtoAtualizado.setQuantidade(produtoDetalhes.getQuantidade());
            produtoAtualizado.setFornecedor(produtoDetalhes.getFornecedor());
            return ResponseEntity.ok(produtoRepository.save(produtoAtualizado));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
        public ResponseEntity<List<Produto>> buscarPorNomeOuCategoria(@RequestParam String termo) {
            List<Produto> produtos = produtoRepository.findByNomeContainingOrCategoriaContaining(termo, termo);
            if (produtos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        if (produto.isPresent()) {
            produtoRepository.delete(produto.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}