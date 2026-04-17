package com.gabriel.simulador_cdb.service;

import com.gabriel.simulador_cdb.controller.DepositoRequest;
import com.gabriel.simulador_cdb.controller.response.ContaResponse;
import com.gabriel.simulador_cdb.entity.Cliente;
import com.gabriel.simulador_cdb.entity.Conta;
import com.gabriel.simulador_cdb.exception.BusinessException;
import com.gabriel.simulador_cdb.exception.ResourceNotFoundException;
import com.gabriel.simulador_cdb.mapper.ContaMapper;
import com.gabriel.simulador_cdb.repository.ClienteRepository;
import com.gabriel.simulador_cdb.repository.ContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@AllArgsConstructor
public class ContaService {

    private final ContaRepository contaRepository;
    private final ContaMapper contaMapper;

    @Transactional
    public ContaResponse depositar(DepositoRequest depositoRequest) {

        Conta conta = contaRepository.findByCliente_IdCliente(depositoRequest.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Conta NÃO encontrada."));

        if(!conta.isAtivo()){
            throw new BusinessException("Conta deve estar ativa");
        }

        if (depositoRequest.getValor().compareTo(BigDecimal.ZERO) <= 0){
            throw new BusinessException("Saldo deve ser positivo");
        }

        Cliente cliente = conta.getCliente();

        if (cliente.getBloqueioCliente() != null && cliente.getBloqueioCliente().getStatusBloqueio().equals("ATIVO")){
            throw new BusinessException("Cliente bloqueado");
        }

        BigDecimal valorDeposito = depositoRequest.getValor();
        conta.setSaldoDisponivel(conta.getSaldoDisponivel().add(valorDeposito));

        contaRepository.save(conta);

        return contaMapper.toResponse(conta);
    }

    public ContaResponse consulta(Long idCliente) {
        Conta conta = contaRepository.findByCliente_IdCliente(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Conta NÃO encontrada."));

        return contaMapper.toResponse(conta);
    }
}
