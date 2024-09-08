package br.com.cpcl.dto;

import java.math.BigDecimal;

import br.com.cpcl.entity.ProdutoCategory;

public record ProdutoRequest(String nome, String descricao, ProdutoCategory categoria, BigDecimal preco, int estoque, String urlImagem, Long comercio_id) {
    
}
