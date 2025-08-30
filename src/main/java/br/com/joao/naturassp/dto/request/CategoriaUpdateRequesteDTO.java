package br.com.joao.naturassp.dto.request;


import javax.validation.constraints.NotNull;

public record CategoriaUpdateRequesteDTO(
        @NotNull
        Long id,
        String nome
) {
}
