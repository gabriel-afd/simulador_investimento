package com.gabriel.simulador_cdb.controller.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RendimentoRequest {

    @NotNull
    private BigDecimal valorAplicado;

}
