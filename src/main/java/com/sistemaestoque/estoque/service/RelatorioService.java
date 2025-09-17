package com.sistemaestoque.estoque.service;

import com.sistemaestoque.estoque.dto.MovimentacaoReportDTO;
import com.sistemaestoque.estoque.dto.ProdutoBaixoEstoqueDTO;
import com.sistemaestoque.estoque.model.MovimentacaoEstoque;
import com.sistemaestoque.estoque.model.Produto;
import com.sistemaestoque.estoque.repository.MovimentacaoEstoqueRepository;
import com.sistemaestoque.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    public List<ProdutoBaixoEstoqueDTO> gerarRelatorioBaixoEstoque(int estoqueMinimo) {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .filter(p -> p.getQuantidade() <= estoqueMinimo)
                .map(p -> new ProdutoBaixoEstoqueDTO(p.getId(), p.getNome(), p.getQuantidade(), estoqueMinimo))
                .collect(Collectors.toList());
    }

    public List<MovimentacaoReportDTO> gerarRelatorioMovimentacoes(LocalDateTime dataInicio, LocalDateTime dataFim) {
        List<MovimentacaoEstoque> movimentacoes = movimentacaoEstoqueRepository.findAll();
        return movimentacoes.stream()
                .filter(m -> m.getDataHora().isAfter(dataInicio) && m.getDataHora().isBefore(dataFim))
                .map(m -> new MovimentacaoReportDTO(m.getId(), m.getProduto().getId(), m.getProduto().getNome(), m.getQuantidade(), m.getTipo(), m.getDataHora()))
                .collect(Collectors.toList());
    }
}