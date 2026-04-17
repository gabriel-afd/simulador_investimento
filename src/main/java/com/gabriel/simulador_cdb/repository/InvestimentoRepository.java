package com.gabriel.simulador_cdb.repository;

import com.gabriel.simulador_cdb.entity.Investimento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {
    Optional<Investimento> findByIdInvestimento(Long idInvestimento);
}
