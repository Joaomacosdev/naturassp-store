package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.model.Produto;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long idProduto,
        String nome,
        String detalhe,
        String linkFoto,
        BigDecimal preco,
        Boolean disponivel,
        Categoria categoria
        ) {
    public ProdutoResponseDTO(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getDetalhe(), produto.getLinkFoto(), produto.getPreco(),  produto.getDisponivel(), produto.getCategoria());
    }


}
