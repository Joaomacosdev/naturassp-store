package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.ItemPedido;

import java.math.BigDecimal;

public record ItemPedidoResponseDTO(
        Long numSeq,
        Integer qtdItem,
        BigDecimal precoUnitario,
        BigDecimal precoTotal,
        ProdutoResponseDTO produto
) {
    public ItemPedidoResponseDTO(ItemPedido item) {
        this(
                item.getNumSeq(),
                item.getQtdItem(),
                item.getPrecoUnitario(),
                item.getPrecoTotal(),
                new ProdutoResponseDTO(item.getProduto())
        );
    }
}
