package br.com.joao.naturassp.service;

import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface ClienteService {

    public EntityModel<ClienteResponseDTO> inserirNovoCliente(ClienteRequestDTO requestDTO);
}
