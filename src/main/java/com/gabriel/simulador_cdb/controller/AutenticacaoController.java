package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.service.AutenticacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AutenticacaoService autenticarService;

    @PostMapping("/autenticar")
    public String autenticar(Authentication authentication){
        return autenticarService.autenticar(authentication);
    }
}
