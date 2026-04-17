package com.gabriel.simulador_cdb.controller;

import com.gabriel.simulador_cdb.controller.request.ProdutoCreateRequest;
import com.gabriel.simulador_cdb.controller.response.ProdutoResponse;
import com.gabriel.simulador_cdb.service.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("cadastrar")
    public ProdutoResponse cadastrar(@Valid @RequestBody ProdutoCreateRequest produtoCreateRequest){
        return produtoService.cadastrar(produtoCreateRequest);
    }

    @GetMapping
    public List<ProdutoResponse> listar(){
        return produtoService.listar();
    }

}
