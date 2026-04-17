package com.gabriel.simulador_cdb.mapper;

import com.gabriel.simulador_cdb.controller.response.InvestimentoResponse;
import com.gabriel.simulador_cdb.entity.Investimento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class InvestimentoMapper {

    public InvestimentoResponse toResponse(Investimento investimento) {

        InvestimentoResponse investimentoResponse = new InvestimentoResponse();
        investimentoResponse.setIdInvestimento(investimento.getIdInvestimento());
        investimentoResponse.setDataContratacao(investimento.getDataContratacao());
        investimentoResponse.setVlrAplicadoInicial(investimento.getVlrAplicadoInicial());
        investimentoResponse.setStatusInvestimento(investimento.getStatusInvestimento());
        investimentoResponse.setDataVencimento(investimento.getDataVencimento());
        investimentoResponse.setTaxaCdi(investimento.getTaxaCdi());
        investimentoResponse.setRendimentoAcumulado(investimento.getRendimentoAcumulado());

        return investimentoResponse;
    }
}
