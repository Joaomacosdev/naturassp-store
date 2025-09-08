package br.com.joao.naturassp.infra.security;

import javax.validation.constraints.NotBlank;

public record DadosRefreshToken(
        @NotBlank
        String refreshToken
) {
}
