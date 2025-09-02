package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.model.Produto;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        String detalhe,
        String linkFoto,
        BigDecimal preco,
        Categoria categoria,
        Boolean disponivel
        ) {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDetalhe(), produto.getLinkFoto(), produto.getPreco(), produto.getCategoria(), produto.getDisponivel());
    }
}
