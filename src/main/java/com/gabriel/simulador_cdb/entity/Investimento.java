package com.gabriel.simulador_cdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "investimento")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInvestimento;

    @ManyToOne
    @JoinColumn(name = "id_cliente",nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_produto", nullable = false)
    private Produto produto;

    private LocalDate dataContratacao;
    private BigDecimal vlrAplicadoInicial;
    @Enumerated(EnumType.STRING)
    private StatusInvestimento statusInvestimento;
    private LocalDate dataVencimento;
    private BigDecimal taxaCdi;
    private BigDecimal rendimentoAcumulado;

    @OneToOne(mappedBy = "investimento", cascade = CascadeType.ALL)
    private Rendimento rendimento;

    public static Investimento criar(Cliente cliente, Produto produto, BigDecimal valor){
        Investimento investimento = new Investimento();

        investimento.cliente = cliente;
        investimento.produto = produto;
        investimento.vlrAplicadoInicial = valor;

        LocalDate hoje = LocalDate.now();
        investimento.setStatusInvestimento(StatusInvestimento.EM_CARENCIA);
        investimento.setDataContratacao(hoje);
        investimento.setDataVencimento(hoje.plusDays(produto.getPrazoDias()));

        return investimento;
    }

    public boolean podeResgatar(Investimento investimento, Produto produto){

        LocalDate dataHoje = LocalDate.now();
        LocalDate periodoContratacao = investimento.getDataContratacao().plusDays(produto.getCarenciaDias());

        if (dataHoje.isAfter(periodoContratacao)){
            return true;
        }
        return false;
    }
}
