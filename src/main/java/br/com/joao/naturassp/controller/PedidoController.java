package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.PedidoRequestDTO;
import br.com.joao.naturassp.dto.response.PedidoResponseDTO;
import br.com.joao.naturassp.service.impl.PedidoServiceImpl;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedido")
@CrossOrigin("*")
public class PedidoController {

    private final PedidoServiceImpl pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<PedidoResponseDTO>> inserirNovoPedido(@RequestBody @Valid PedidoRequestDTO requestDTO, UriComponentsBuilder uriBuilder){
        EntityModel<PedidoResponseDTO> pedido = pedidoService.inserirPedido(requestDTO);
        var uri = uriBuilder.path("/pedido/{id}").buildAndExpand(pedido.getContent().idPedido()).toUri();
        return ResponseEntity.created(uri).body(pedido);
    }
}
