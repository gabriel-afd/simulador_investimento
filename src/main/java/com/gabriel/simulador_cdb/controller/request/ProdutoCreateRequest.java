package com.gabriel.simulador_cdb.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoCreateRequest {

    @NotBlank
    private String nomeProduto;
    @NotBlank
    private String tipoProduto;
    private BigDecimal percentualCdi;
    private Integer carenciaDias;
    private Integer prazoDias;
    private BigDecimal taxaFixaAnual;
    private String risco;
    @NotNull
    private BigDecimal valorMinimoAplicacao;
}
