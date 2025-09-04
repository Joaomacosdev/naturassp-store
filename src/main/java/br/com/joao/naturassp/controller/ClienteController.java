package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import br.com.joao.naturassp.service.ClienteService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<ClienteResponseDTO>> inserirNovoCliente(@RequestBody @Valid ClienteRequestDTO requestDTO, UriComponentsBuilder uriBuilder){
        EntityModel<ClienteResponseDTO> clienteResponse = clienteService.inserirNovoCliente(requestDTO);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteResponse.getContent().id()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }
}
