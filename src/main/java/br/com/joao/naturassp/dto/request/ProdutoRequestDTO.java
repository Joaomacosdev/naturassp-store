package br.com.joao.naturassp.dto.request;

import br.com.joao.naturassp.model.Categoria;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome do produto é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,
        @Size(max = 500, message = "O detalhe deve ter no máximo 500 caracteres")
        String detalhe,
        @NotBlank(message = "O link da foto é obrigatório")
        @Size(max = 255, message = "O link da foto deve ter no máximo 255 caracteres")
        String linkFoto,
        @NotNull(message = "O preço do produto é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço deve ser maior que zero")
        BigDecimal preco,
        @NotNull(message = "A categoria é obrigatória")
        Categoria categoria,
        @NotNull(message = "A disponibilidade deve ser informada")
        Boolean disponivel
) {
}
