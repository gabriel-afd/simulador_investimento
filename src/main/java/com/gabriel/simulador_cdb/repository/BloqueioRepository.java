package com.gabriel.simulador_cdb.repository;

import com.gabriel.simulador_cdb.entity.BloqueioCliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BloqueioRepository extends JpaRepository<BloqueioCliente, Long> {
    Optional<BloqueioCliente> findByCliente_IdCliente(Long idCliente);
}
