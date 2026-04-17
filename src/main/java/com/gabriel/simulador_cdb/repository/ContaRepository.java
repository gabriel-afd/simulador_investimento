package com.gabriel.simulador_cdb.repository;

import com.gabriel.simulador_cdb.entity.Conta;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {

    Optional<Conta> findByCliente_IdCliente(Long idCliente);
}
