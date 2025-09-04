package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.model.Pedido;
import br.com.joao.naturassp.service.impl.PedidoServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoServiceImpl pedidoService;

    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> inserirNovoPedido(Pedido novo){
        novo = pedidoService.inserirPedido(novo);

        if (novo != null){
            return ResponseEntity.status(201).body(novo);
        } else {
            return ResponseEntity.status(400).build();
        }
    }
}
