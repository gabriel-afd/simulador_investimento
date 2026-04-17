package com.gabriel.simulador_cdb.service;

import com.gabriel.simulador_cdb.controller.request.RendimentoRequest;
import com.gabriel.simulador_cdb.controller.response.RendimentoResponse;
import com.gabriel.simulador_cdb.entity.Investimento;
import com.gabriel.simulador_cdb.entity.Produto;
import com.gabriel.simulador_cdb.entity.Rendimento;
import com.gabriel.simulador_cdb.exception.BusinessException;
import com.gabriel.simulador_cdb.exception.ResourceNotFoundException;
import com.gabriel.simulador_cdb.factory.RendimentoStrategyFactory;
import com.gabriel.simulador_cdb.mapper.RendimentoMapper;
import com.gabriel.simulador_cdb.repository.InvestimentoRepository;
import com.gabriel.simulador_cdb.repository.RendimentoRepository;
import com.gabriel.simulador_cdb.strategy.RendimentoStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RendimentoService {

    private final RendimentoRepository rendimentoRepository;
    private final InvestimentoRepository investimentoRepository;
    private final RendimentoMapper rendimentoMapper;
    private final RendimentoStrategyFactory factory;


    @Transactional
    public RendimentoResponse calcular(Long investimentoId) {

        Investimento investimento = investimentoRepository.findByIdInvestimento(investimentoId)
                .orElseThrow(() -> new ResourceNotFoundException("Investimento não encontrado"));

        if (rendimentoRepository.existsByInvestimento_IdInvestimento(investimentoId)) {
            throw new BusinessException("Rendimento já calculado para este investimento");
        }

        RendimentoStrategy strategy = factory.getStrategy(investimento.getProduto().getTipoProduto());

        BigDecimal rendimento = strategy.calcular(investimento);

        BigDecimal valorAplicado = investimento.getVlrAplicadoInicial();

        Rendimento r = new Rendimento();
        r.setInvestimento(investimento);
        r.setValorAplicado(valorAplicado);
        r.setValorRendimento(rendimento);
        r.setValorTotal(valorAplicado.add(rendimento));
        r.setDataCalculo(LocalDate.now());

        rendimentoRepository.save(r);

        return rendimentoMapper.toResponse(r);

    }
}
