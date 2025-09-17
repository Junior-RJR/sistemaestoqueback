package com.sistemaestoque.estoque.controller;

import com.sistemaestoque.estoque.dto.MovimentacaoReportDTO;
import com.sistemaestoque.estoque.dto.ProdutoBaixoEstoqueDTO;
import com.sistemaestoque.estoque.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/baixo-estoque")
    public ResponseEntity<List<ProdutoBaixoEstoqueDTO>> getProdutosBaixoEstoque(@RequestParam int estoqueMinimo) {
        List<ProdutoBaixoEstoqueDTO> relatorio = relatorioService.gerarRelatorioBaixoEstoque(estoqueMinimo);
        return ResponseEntity.ok(relatorio);
    }

    @GetMapping("/movimentacoes")
    public ResponseEntity<List<MovimentacaoReportDTO>> getMovimentacoes(@RequestParam String dataInicio, @RequestParam String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
        LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);

        List<MovimentacaoReportDTO> relatorio = relatorioService.gerarRelatorioMovimentacoes(inicio, fim);
        return ResponseEntity.ok(relatorio);
    }
}