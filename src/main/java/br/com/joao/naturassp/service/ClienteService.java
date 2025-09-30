package br.com.joao.naturassp.service;

import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.request.ClienteUpdateDTO;
import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import org.springframework.hateoas.EntityModel;

public interface ClienteService {

    public EntityModel<ClienteResponseDTO> inserirNovoCliente(ClienteRequestDTO requestDTO);

    public EntityModel<ClienteResponseDTO> buscarPeloCpf(String cpf);
    public  EntityModel<ClienteResponseDTO> atualizarDadosCliente(ClienteUpdateDTO updateDTO);
}
