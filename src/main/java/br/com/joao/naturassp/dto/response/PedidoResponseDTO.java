package br.com.joao.naturassp.dto.response;

import br.com.joao.naturassp.model.Cliente;
import br.com.joao.naturassp.model.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PedidoResponseDTO(
        Long idPedido,
        LocalDate dataPedido,
        BigDecimal valorTotal,
        String observacoes,
        Integer status,
        @JsonIgnoreProperties({"endereco"})
        Cliente cliente,
        List<ItemPedidoResponseDTO> itensPedidos
) {
    public PedidoResponseDTO(Pedido pedido) {
        this(pedido.getId(), pedido.getDataPedido(), pedido.getValorTotal(), pedido.getObservacoes(), pedido.getStatus(),pedido.getCliente(), pedido.getItensPedidos().stream().map(ItemPedidoResponseDTO::new)
                .toList());
    }
}
