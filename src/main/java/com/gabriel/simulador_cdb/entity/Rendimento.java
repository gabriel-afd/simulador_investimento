package com.gabriel.simulador_cdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rendimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRendimento;

    private BigDecimal valorAplicado;

    private BigDecimal valorRendimento;

    private BigDecimal valorTotal;

    private LocalDate dataCalculo;

    @OneToOne
    @JoinColumn(name = "id_investimento", nullable = false)
    private Investimento investimento;
}
