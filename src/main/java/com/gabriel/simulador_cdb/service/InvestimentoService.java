package com.gabriel.simulador_cdb.service;

import com.gabriel.simulador_cdb.controller.request.InvestimentoRequest;
import com.gabriel.simulador_cdb.controller.request.InvestimentoResgateRequest;
import com.gabriel.simulador_cdb.controller.response.InvestimentoResponse;
import com.gabriel.simulador_cdb.entity.*;
import com.gabriel.simulador_cdb.exception.BusinessException;
import com.gabriel.simulador_cdb.exception.ResourceNotFoundException;
import com.gabriel.simulador_cdb.mapper.InvestimentoMapper;
import com.gabriel.simulador_cdb.repository.ClienteRepository;
import com.gabriel.simulador_cdb.repository.InvestimentoRepository;
import com.gabriel.simulador_cdb.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InvestimentoService {

    private final InvestimentoRepository investimentoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final InvestimentoMapper investimentoMapper;

    @Transactional
    public InvestimentoResponse contratar(InvestimentoRequest investimentoRequest) {

        Cliente cliente = clienteRepository.findByIdCliente(investimentoRequest.getIdCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        Produto produto = produtoRepository.findByIdProduto(investimentoRequest.getIdProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não existe"));

        if (!produto.isAtivo()){
            throw new BusinessException("Produto não pode ser contratado, pois não está mais ativo");
        }

        BloqueioCliente bloqueioCliente = cliente.getBloqueioCliente();

        if (bloqueioCliente != null && bloqueioCliente.getStatusBloqueio().equals(StatusBloqueio.ATIVO)){
            throw new BusinessException("Cliente bloqueado");
        }

        if (investimentoRequest.getValor().compareTo(produto.getValorMinimoAplicacao()) < 0){
            throw new BusinessException("Você deve aplicar o valor minímo para este produto!");
        }

        Conta conta = cliente.getConta();

        if (!conta.isAtivo()) {
            throw new BusinessException("Conta inativa");
        }

        if (conta.getSaldoDisponivel().compareTo(investimentoRequest.getValor()) < 0){
            throw new BusinessException("Você não possui saldo minímo para aplicar");
        }

        conta.setSaldoDisponivel(conta.getSaldoDisponivel().subtract(investimentoRequest.getValor()));

        Investimento investimento = Investimento.criar(cliente, produto, investimentoRequest.getValor());

        investimentoRepository.save(investimento);

        return investimentoMapper.toResponse(investimento);
    }

    @Transactional
    public InvestimentoResponse resgatar(Long idInvestimento,InvestimentoResgateRequest resgateRequest) {

        Investimento investimento = investimentoRepository.findByIdInvestimento(idInvestimento)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não existe"));

        Cliente cliente = investimento.getCliente();
        Produto produto = investimento.getProduto();

        if (cliente.getBloqueioCliente() != null && cliente.getBloqueioCliente().getStatusBloqueio() == StatusBloqueio.ATIVO){
            throw new BusinessException("Para recuperar o investimento, o cliente NÃO pode estar bloqueado");
        }

        if (!investimento.podeResgatar(investimento, produto)){
            throw new BusinessException("Você não pode resgatar seu investimento pois ainda está em periodo de carência");
        }

        Conta conta = cliente.getConta();
        conta.setSaldoDisponivel(conta.getSaldoDisponivel().add(investimento.getRendimentoAcumulado()));

        investimento.setStatusInvestimento(StatusInvestimento.RESGATADO);
        investimento.setCliente(cliente);
        investimento.setProduto(produto);

        investimentoRepository.save(investimento);

        return investimentoMapper.toResponse(investimento);
    }
}
