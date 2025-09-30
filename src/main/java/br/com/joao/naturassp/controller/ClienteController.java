package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.ClienteRequestDTO;
import br.com.joao.naturassp.dto.request.ClienteUpdateDTO;
import br.com.joao.naturassp.dto.response.ClienteResponseDTO;
import br.com.joao.naturassp.service.ClienteService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/cliente")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<ClienteResponseDTO>> inserirNovoCliente(@RequestBody @Valid ClienteRequestDTO requestDTO, UriComponentsBuilder uriBuilder){
        EntityModel<ClienteResponseDTO> clienteResponse = clienteService.inserirNovoCliente(requestDTO);
        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(clienteResponse.getContent().idCliente()).toUri();
        return ResponseEntity.created(uri).body(clienteResponse);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<EntityModel<ClienteResponseDTO>> buscarPeloTelefone(@PathVariable String cpf){
        var cliente = clienteService.buscarPeloCpf(cpf);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping
    public ResponseEntity<EntityModel<ClienteResponseDTO>> atualizarCliente(@RequestBody @Valid ClienteUpdateDTO updateDTO){
        var cliente = clienteService.atualizarDadosCliente(updateDTO);
        return ResponseEntity.ok().body(cliente);
    }
}
