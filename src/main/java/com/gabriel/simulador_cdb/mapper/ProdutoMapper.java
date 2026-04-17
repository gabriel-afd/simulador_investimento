package com.gabriel.simulador_cdb.mapper;

import com.gabriel.simulador_cdb.controller.request.ProdutoCreateRequest;
import com.gabriel.simulador_cdb.controller.response.ProdutoResponse;
import com.gabriel.simulador_cdb.entity.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    public Produto toEntity(ProdutoCreateRequest produtoCreateRequest) {
        Produto produto = new Produto();

        produto.setNomeProduto(produtoCreateRequest.getNomeProduto());
        produto.setTipoProduto(produtoCreateRequest.getTipoProduto());
        produto.setPercentualCdi(produtoCreateRequest.getPercentualCdi());
        produto.setCarenciaDias(produtoCreateRequest.getCarenciaDias());
        produto.setPrazoDias(produtoCreateRequest.getPrazoDias());
        produto.setTaxaFixaAnual(produtoCreateRequest.getTaxaFixaAnual());
        produto.setRisco(produtoCreateRequest.getRisco());
        produto.setValorMinimoAplicacao(produtoCreateRequest.getValorMinimoAplicacao());

        return produto;
    }

    public ProdutoResponse toResponse(Produto produto) {

        ProdutoResponse produtoResponse = new ProdutoResponse();

        produtoResponse.setIdProduto(produto.getIdProduto());
        produtoResponse.setNomeProduto(produto.getNomeProduto());
        produtoResponse.setTipoProduto(produto.getTipoProduto());
        produtoResponse.setPercentualCdi(produto.getPercentualCdi());
        produtoResponse.setCarenciaDias(produto.getCarenciaDias());
        produtoResponse.setPrazoDias(produto.getPrazoDias());
        produtoResponse.setTaxaFixaAnual(produto.getTaxaFixaAnual());
        produtoResponse.setRisco(produto.getRisco());
        produtoResponse.setValorMinimoAplicacao(produto.getValorMinimoAplicacao());
        produtoResponse.setAtivo(produto.isAtivo());

        return produtoResponse;
    }
}
