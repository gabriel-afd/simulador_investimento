package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.controller.response.ContaResponse;
import com.gabriel.simulador_cdb.service.ContaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {

    private final ContaService contaService;

    @PostMapping("/depositar")
    public ContaResponse depositar(@Valid @RequestBody DepositoRequest depositoRequest){
        return contaService.depositar(depositoRequest);
    }

    @GetMapping("/saldo/{idCliente}")
    public ContaResponse consultar(@PathVariable Long idCliente){
        return contaService.consulta(idCliente);
    }
}
