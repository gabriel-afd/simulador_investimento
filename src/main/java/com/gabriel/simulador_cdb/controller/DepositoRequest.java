package com.gabriel.simulador_cdb.controller;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepositoRequest {
    @NotNull
    private Long idCliente;
    @NotNull
    private BigDecimal valor;
}
