package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.NotBlank;

public record CategoriaRequestDTO(
        @NotBlank
        String nome
) {
}
