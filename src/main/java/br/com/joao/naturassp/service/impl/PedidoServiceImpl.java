package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.model.ItemPedido;
import br.com.joao.naturassp.model.Pedido;
import br.com.joao.naturassp.repository.PedidoRepository;
import br.com.joao.naturassp.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PedidoServiceImpl implements PedidoService{

   private final PedidoRepository pedidoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }


    @Transactional(readOnly = false)
    @Override
    public Pedido inserirPedido(Pedido pedido) {

        for (ItemPedido item: pedido.getItensPedidos()){
            item.setPrecoUnitario(item.getProduto().getPreco());

        }

        for (ItemPedido item: pedido.getItensPedidos()){
            item.setPedido(pedido);
        }

        return pedidoRepository.save(pedido);
    }
}
