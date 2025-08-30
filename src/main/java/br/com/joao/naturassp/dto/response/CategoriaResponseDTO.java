package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Categoria;

public record CategoriaResponseDTO(
        Long id,
        String nome
) {
    public CategoriaResponseDTO(Categoria categoria) {
        this(categoria.getId(), categoria.getNome());
    }
}
