package com.gabriel.simulador_cdb.factory;

import com.gabriel.simulador_cdb.exception.BusinessException;
import com.gabriel.simulador_cdb.strategy.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class RendimentoStrategyFactory {

    private final Map<String, RendimentoStrategy> strategies;

    public RendimentoStrategyFactory(Map<String, RendimentoStrategy> strategies) {
        this.strategies = strategies;
    }

    public RendimentoStrategy getStrategy(String tipoInvestimento){
        RendimentoStrategy strategy = strategies.get(tipoInvestimento);

        if (strategy == null) {
            throw new BusinessException("Tipo de produto não suportado");
        }

        return strategy;
    }
}

