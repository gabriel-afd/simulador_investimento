package com.gabriel.simulador_cdb.service;

import com.gabriel.simulador_cdb.controller.request.ProdutoCreateRequest;
import com.gabriel.simulador_cdb.controller.response.ProdutoResponse;
import com.gabriel.simulador_cdb.entity.Produto;
import com.gabriel.simulador_cdb.mapper.ProdutoMapper;
import com.gabriel.simulador_cdb.repository.ProdutoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ProdutoMapper produtoMapper;

    @Transactional
    public ProdutoResponse cadastrar(ProdutoCreateRequest produtoCreateRequest) {

        Produto produto = produtoMapper.toEntity(produtoCreateRequest);

        produto.setAtivo(true);

        produtoRepository.save(produto);

        return produtoMapper.toResponse(produto);
    }


    public List<ProdutoResponse> listar() {
        return produtoRepository.findByIsAtivoTrue()
                .stream()
                .map(produto -> {
                    ProdutoResponse produtoResponse = produtoMapper.toResponse(produto);
                    return produtoResponse;
                }).toList();

    }
}
