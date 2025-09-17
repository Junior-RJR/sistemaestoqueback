package com.sistemaestoque.estoque.dto;

import com.sistemaestoque.estoque.model.TipoMovimentacao;
import java.time.LocalDateTime;

public class MovimentacaoReportDTO {
    private Long movimentacaoId;
    private Long produtoId;
    private String nomeProduto;
    private int quantidade;
    private TipoMovimentacao tipo;
    private LocalDateTime dataHora;

    public MovimentacaoReportDTO(Long movimentacaoId, Long produtoId, String nomeProduto, int quantidade, TipoMovimentacao tipo, LocalDateTime dataHora) {
        this.movimentacaoId = movimentacaoId;
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.tipo = tipo;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public Long getMovimentacaoId() {
        return movimentacaoId;
    }

    public void setMovimentacaoId(Long movimentacaoId) {
        this.movimentacaoId = movimentacaoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}