package com.gabriel.simulador_cdb.controller.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponse {

    private Long idProduto;
    private String nomeProduto;
    private String tipoProduto;
    private BigDecimal percentualCdi;
    private Integer carenciaDias;
    private Integer prazoDias;
    private BigDecimal taxaFixaAnual;
    private String risco;
    private BigDecimal valorMinimoAplicacao;
    private boolean isAtivo;
}
