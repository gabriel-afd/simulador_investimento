package com.gabriel.simulador_cdb.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContaResponse {

    private Long idConta;
    private BigDecimal saldoDisponivel;
    private LocalDate dataAbertura;
    private boolean ativo;
}
