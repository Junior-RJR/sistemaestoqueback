package com.sistemaestoque.estoque.repository;

import com.sistemaestoque.estoque.model.Cotacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
}