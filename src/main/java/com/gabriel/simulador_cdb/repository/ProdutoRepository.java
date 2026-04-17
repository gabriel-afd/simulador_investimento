package com.gabriel.simulador_cdb.repository;

import com.gabriel.simulador_cdb.entity.Produto;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByIsAtivoTrue();

    Optional<Produto> findByIdProduto(Long idProduto);
}
