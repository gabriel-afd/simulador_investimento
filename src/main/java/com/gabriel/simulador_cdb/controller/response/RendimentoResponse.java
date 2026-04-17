package com.gabriel.simulador_cdb.controller.response;

import com.gabriel.simulador_cdb.entity.Investimento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class RendimentoResponse {

    private Long idRendimento;

    private BigDecimal valorAplicado;

    private BigDecimal valorRendimento;

    private BigDecimal valorTotal;

    private LocalDate dataCalculo;
}
