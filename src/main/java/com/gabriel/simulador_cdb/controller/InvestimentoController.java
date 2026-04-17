package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.controller.request.InvestimentoRequest;
import com.gabriel.simulador_cdb.controller.request.InvestimentoResgateRequest;
import com.gabriel.simulador_cdb.controller.response.InvestimentoResponse;
import com.gabriel.simulador_cdb.service.InvestimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/investimento")
@RequiredArgsConstructor
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    @PostMapping("/contratar")
    public InvestimentoResponse contratar(@Valid @RequestBody InvestimentoRequest investimentoRequest){

        return investimentoService.contratar(investimentoRequest);
    }

    @PostMapping("/resgatar/{idInvestimento}")
    public InvestimentoResponse resgatar(@PathVariable Long idInvestimento, @Valid InvestimentoResgateRequest resgateRequest){
        return investimentoService.resgatar(idInvestimento, resgateRequest);
    }
}
