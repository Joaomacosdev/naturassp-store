package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.controller.ClienteController;
import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import br.com.joao.naturassp.infra.exception.DuplicateResourceException;
import br.com.joao.naturassp.model.Cliente;
import br.com.joao.naturassp.repository.ClienteRepository;
import br.com.joao.naturassp.service.ClienteService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ClienteServiceImpl implements ClienteService{

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    @Override
    public EntityModel<ClienteResponseDTO> inserirNovoCliente(ClienteRequestDTO requestDTO) {

        if (clienteRepository.existsByEmail(requestDTO.email())){
            throw new DuplicateResourceException("Cliente com email: " + requestDTO.email() + " já cadastrado");
        }


        if (clienteRepository.existsByCpf(requestDTO.cpf())){
            throw new DuplicateResourceException("Cliente com cpf: " + requestDTO.cpf() + " já cadastrado");
        }

        var cliente = clienteRepository.save(new Cliente(requestDTO));
        var dto = new ClienteResponseDTO(cliente);
        var links = getAllLinks(dto);

        return EntityModel.of(dto, links);
    }

    private Collection<Link> getAllLinks(ClienteResponseDTO dto){
        return List.of(
                linkTo(methodOn(ClienteController.class).inserirNovoCliente(null, null)).withRel("create").withType("POST")
        );
    }
}
