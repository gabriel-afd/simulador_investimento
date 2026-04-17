package com.gabriel.simulador_cdb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutenticacaoService {

    private final JwtService jwtService;

    public String autenticar(Authentication authentication){
        return jwtService.gerarToken(authentication);
    }
}
