package com.sistemaestoque.estoque.repository;

import com.sistemaestoque.estoque.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
}