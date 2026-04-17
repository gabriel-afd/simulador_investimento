package com.gabriel.simulador_cdb.strategy;

import com.gabriel.simulador_cdb.entity.Investimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("FUNDO_DI")
public class FundoDiStrategy implements RendimentoStrategy{

    @Override
    public BigDecimal calcular(Investimento investimento) {

        BigDecimal valor = investimento.getVlrAplicadoInicial();
        BigDecimal taxa = investimento.getProduto().getPercentualCdi();
        BigDecimal taxaAdministracao = investimento.getProduto().getTaxaFixaAnual();

        BigDecimal rendimento = valor.multiply(taxa);

        if (taxaAdministracao != null) {
            rendimento = rendimento.subtract(valor.multiply(taxaAdministracao));
        }

        return rendimento;
    }
}
