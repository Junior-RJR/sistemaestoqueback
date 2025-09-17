package com.sistemaestoque.estoque.dto;

public class ProdutoBaixoEstoqueDTO {
    private Long id;
    private String nome;
    private int quantidadeAtual;
    private int estoqueMinimo;

    public ProdutoBaixoEstoqueDTO(Long id, String nome, int quantidadeAtual, int estoqueMinimo) {
        this.id = id;
        this.nome = nome;
        this.quantidadeAtual = quantidadeAtual;
        this.estoqueMinimo = estoqueMinimo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
}