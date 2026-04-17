package com.gabriel.simulador_cdb.mapper;

import com.gabriel.simulador_cdb.controller.response.ContaResponse;
import com.gabriel.simulador_cdb.entity.Conta;
import org.springframework.stereotype.Component;

@Component
public class ContaMapper {
    public ContaResponse toResponse(Conta conta) {

        ContaResponse contaResponse = new ContaResponse();

        contaResponse.setIdConta(conta.getIdConta());
        contaResponse.setSaldoDisponivel(conta.getSaldoDisponivel());
        contaResponse.setDataAbertura(conta.getDataAbertura());
        contaResponse.setAtivo(conta.isAtivo());

        return contaResponse;
    }
}
