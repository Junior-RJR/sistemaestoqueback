package com.sistemaestoque.estoque.service;

import com.sistemaestoque.estoque.model.MovimentacaoEstoque;
import com.sistemaestoque.estoque.model.Produto;
import com.sistemaestoque.estoque.model.TipoMovimentacao;
import com.sistemaestoque.estoque.repository.MovimentacaoEstoqueRepository;
import com.sistemaestoque.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Transactional
    public Produto registrarEntrada(Long produtoId, int quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            int novaQuantidade = produto.getQuantidade() + quantidade;
            produto.setQuantidade(novaQuantidade);
            produtoRepository.save(produto);

            // Registra a movimentação no histórico
            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(produto, quantidade, TipoMovimentacao.ENTRADA);
            movimentacaoEstoqueRepository.save(movimentacao);

            return produto;
        }
        return null; // ou lançar uma exceção
    }

    @Transactional
    public Produto registrarSaida(Long produtoId, int quantidade) {
        Optional<Produto> optionalProduto = produtoRepository.findById(produtoId);
        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            if (produto.getQuantidade() < quantidade) {
                throw new IllegalArgumentException("Estoque insuficiente para a saída.");
            }
            int novaQuantidade = produto.getQuantidade() - quantidade;
            produto.setQuantidade(novaQuantidade);
            produtoRepository.save(produto);

            // Registra a movimentação no histórico
            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque(produto, quantidade, TipoMovimentacao.SAIDA);
            movimentacaoEstoqueRepository.save(movimentacao);

            return produto;
        }
        return null; // ou lançar uma exceção
    }
}