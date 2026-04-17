package com.gabriel.simulador_cdb.repository;

import com.gabriel.simulador_cdb.entity.Rendimento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RendimentoRepository extends JpaRepository<Rendimento, Long> {
    boolean existsByInvestimento_IdInvestimento(Long investimentoId);
}
