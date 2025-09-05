package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

public record PedidoRequestDTO(

        @NotNull(message = "O valor total é obrigatório")
        @DecimalMin(value = "0.01", message = "O valor total deve ser maior que zero")
        BigDecimal valorTotal,

        @Size(max = 500, message = "As observações devem ter no máximo 500 caracteres")
        String observacoes,

        @NotNull(message = "O status é obrigatório")
        @Min(value = 0, message = "Status inválido")
        @Max(value = 5, message = "Status inválido")
        Integer status,

        @NotNull(message = "O cliente é obrigatório")
        Long idCliente,

        @NotEmpty(message = "O pedido deve conter pelo menos um item")
        List<ItemPedidoRequestDTO> itensPedidos
) {
}
