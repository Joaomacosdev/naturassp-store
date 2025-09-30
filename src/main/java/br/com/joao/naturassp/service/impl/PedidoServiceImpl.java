package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.controller.PedidoController;
import br.com.joao.naturassp.dto.request.ItemPedidoRequestDTO;
import br.com.joao.naturassp.dto.request.PedidoRequestDTO;
import br.com.joao.naturassp.dto.response.PedidoResponseDTO;
import br.com.joao.naturassp.model.Cliente;
import br.com.joao.naturassp.model.ItemPedido;
import br.com.joao.naturassp.model.Pedido;
import br.com.joao.naturassp.model.Produto;
import br.com.joao.naturassp.repository.ClienteRepository;
import br.com.joao.naturassp.repository.PedidoRepository;
import br.com.joao.naturassp.repository.ProdutoRepository;
import br.com.joao.naturassp.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }


    @Transactional(readOnly = false)
    @Override
    public EntityModel<PedidoResponseDTO> inserirPedido(PedidoRequestDTO requestDTO) {

        var pedido = new Pedido(requestDTO);

        Cliente cliente = clienteRepository.findById(requestDTO.idCliente()).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        pedido.setCliente(cliente);


        List<ItemPedido> itens = criarItensPedido(requestDTO.itensPedidos(), pedido);
        pedido.setItensPedidos(itens);

        BigDecimal valorTotal = calcularValorTotal(itens);
        pedido.setValorTotal(valorTotal);

        pedido = pedidoRepository.save(pedido);

        var dto = new PedidoResponseDTO(pedido);
        var links = getAllLinks(dto);

        return EntityModel.of(dto, links);
    }


    private List<ItemPedido> criarItensPedido(List<ItemPedidoRequestDTO> itensDTO, Pedido pedido) {
        return itensDTO.stream().map(itemDTO -> {
            Produto produto = produtoRepository.findById(itemDTO.idProduto()).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado: " + itemDTO.idProduto()));

            ItemPedido item = new ItemPedido();

            item.setProduto(produto);
            item.setQtdItem(itemDTO.qtdItem());
            item.setPrecoUnitario(produto.getPreco());

            BigDecimal precoTotal = produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.qtdItem()));
            item.setPrecoTotal(precoTotal);

            item.setPedido(pedido);


            return item;
        }).toList();
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream().map(ItemPedido::getPrecoTotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    private Collection<Link> getAllLinks(PedidoResponseDTO dto) {
        return List.of(linkTo(methodOn(PedidoController.class).inserirNovoPedido(null, null)).withRel("create").withType("POST"));
    }
}
