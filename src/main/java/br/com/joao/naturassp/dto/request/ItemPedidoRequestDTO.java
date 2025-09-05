package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record ItemPedidoRequestDTO(
        @NotNull(message = "A quantidade do item é obrigatória")
        @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
        Integer qtdItem,

        @NotNull(message = "O preço unitário é obrigatório")
        @DecimalMin(value = "0.01", message = "O preço unitário deve ser maior que zero")
        BigDecimal precoUnitario,

        @NotNull(message = "O ID do produto é obrigatório")
        Long idProduto
) {
}
