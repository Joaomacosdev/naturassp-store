package br.com.joao.naturassp.dto.request;

import javax.validation.constraints.*;
import java.util.List;

public record PedidoRequestDTO(
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
