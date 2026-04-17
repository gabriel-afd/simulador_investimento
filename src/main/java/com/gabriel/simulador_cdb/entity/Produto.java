package com.gabriel.simulador_cdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
    private List<Investimento> investimentos;
}
