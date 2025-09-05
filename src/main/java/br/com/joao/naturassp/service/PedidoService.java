package br.com.joao.naturassp.service;

import br.com.joao.naturassp.dto.request.PedidoRequestDTO;
import br.com.joao.naturassp.dto.response.PedidoResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface PedidoService {

    public EntityModel<PedidoResponseDTO> inserirPedido(PedidoRequestDTO requestDTO);
}
