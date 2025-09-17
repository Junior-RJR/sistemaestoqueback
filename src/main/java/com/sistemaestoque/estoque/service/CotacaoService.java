package com.sistemaestoque.estoque.service;

import com.sistemaestoque.estoque.model.Cotacao;
import com.sistemaestoque.estoque.model.Fornecedor;
import com.sistemaestoque.estoque.model.Produto;
import com.sistemaestoque.estoque.repository.CotacaoRepository;
import com.sistemaestoque.estoque.repository.FornecedorRepository;
import com.sistemaestoque.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CotacaoService {

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Cotacao> listarTodas() {
        return cotacaoRepository.findAll();
    }

    public Cotacao salvarCotacao(Long produtoId, Long fornecedorId, double preco) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        Fornecedor fornecedor = fornecedorRepository.findById(fornecedorId)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor não encontrado"));

        Cotacao cotacao = new Cotacao(produto, fornecedor, preco);
        return cotacaoRepository.save(cotacao);
    }
}