package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.controller.request.RendimentoRequest;
import com.gabriel.simulador_cdb.controller.response.RendimentoResponse;
import com.gabriel.simulador_cdb.service.RendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rendimento")
@RequiredArgsConstructor
public class RendimentoController {

    private final RendimentoService rendimentoService;

    @PostMapping("/calcular/{investimentoId}")
    public RendimentoResponse calcular(@PathVariable Long investimentoId){
        return rendimentoService.calcular(investimentoId);
    }
}
