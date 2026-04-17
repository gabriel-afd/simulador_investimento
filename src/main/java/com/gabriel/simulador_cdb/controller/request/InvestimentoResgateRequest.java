package com.gabriel.simulador_cdb.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class InvestimentoResgateRequest {

    @NotNull
    BigDecimal valor;
}
