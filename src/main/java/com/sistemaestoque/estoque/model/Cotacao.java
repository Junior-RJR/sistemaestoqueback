package com.sistemaestoque.estoque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cotacoes")
public class Cotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id", nullable = false)
    private Fornecedor fornecedor;

    private double preco;

    private LocalDateTime dataCotacao;

    // Construtores
    public Cotacao() {
        this.dataCotacao = LocalDateTime.now();
    }

    public Cotacao(Produto produto, Fornecedor fornecedor, double preco) {
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.dataCotacao = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataCotacao() {
        return dataCotacao;
    }

    public void setDataCotacao(LocalDateTime dataCotacao) {
        this.dataCotacao = dataCotacao;
    }
}