package com.gabriel.simulador_cdb.mapper;

import com.gabriel.simulador_cdb.controller.response.RendimentoResponse;
import com.gabriel.simulador_cdb.entity.Rendimento;
import org.springframework.stereotype.Component;

@Component
public class RendimentoMapper {
    public RendimentoResponse toResponse(Rendimento rendimento) {

        RendimentoResponse rendimentoResponse = new RendimentoResponse();

        rendimentoResponse.setIdRendimento(rendimento.getIdRendimento());
        rendimentoResponse.setValorAplicado(rendimento.getValorAplicado());
        rendimentoResponse.setValorRendimento(rendimento.getValorRendimento());
        rendimentoResponse.setValorTotal(rendimento.getValorTotal());
        rendimentoResponse.setDataCalculo(rendimento.getDataCalculo());

        return  rendimentoResponse;
    }
}
