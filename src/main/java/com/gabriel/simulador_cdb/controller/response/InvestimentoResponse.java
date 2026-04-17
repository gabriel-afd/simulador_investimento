package com.gabriel.simulador_cdb.controller.response;

import com.gabriel.simulador_cdb.entity.Cliente;
import com.gabriel.simulador_cdb.entity.Produto;
import com.gabriel.simulador_cdb.entity.Rendimento;
import com.gabriel.simulador_cdb.entity.StatusInvestimento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InvestimentoResponse {

    private Long idInvestimento;
    private LocalDate dataContratacao;
    private BigDecimal vlrAplicadoInicial;
    private StatusInvestimento statusInvestimento;
    private LocalDate dataVencimento;
    private BigDecimal taxaCdi;
    private BigDecimal rendimentoAcumulado;
}
