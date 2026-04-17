package com.gabriel.simulador_cdb.strategy;

import com.gabriel.simulador_cdb.entity.Investimento;

import java.math.BigDecimal;

public interface RendimentoStrategy {
    BigDecimal calcular(Investimento investimento);
}
