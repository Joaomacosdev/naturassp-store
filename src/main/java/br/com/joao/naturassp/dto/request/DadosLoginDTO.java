package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.NotBlank;

public record DadosLoginDTO(
        @NotBlank String email,
        @NotBlank String senha) {
}
