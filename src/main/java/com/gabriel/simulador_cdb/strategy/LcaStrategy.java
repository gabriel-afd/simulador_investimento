package com.gabriel.simulador_cdb.strategy;

import com.gabriel.simulador_cdb.entity.Investimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("LCA")
public class LcaStrategy implements RendimentoStrategy{

    @Override
    public BigDecimal calcular(Investimento investimento) {

        BigDecimal valor = investimento.getVlrAplicadoInicial();
        BigDecimal taxa = investimento.getProduto().getPercentualCdi();

        return valor.multiply(taxa);
    }
}
