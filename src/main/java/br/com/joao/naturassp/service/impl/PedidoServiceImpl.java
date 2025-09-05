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
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PedidoServiceImpl implements PedidoService{

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

        Pedido pedido = montarPedido(requestDTO);
        Cliente cliente = buscarCliente(requestDTO.idCliente());
        pedido.setCliente(cliente);

        List<ItemPedido> itensDoPedido = montarItensPedido(pedido, requestDTO.itensPedidos());
        pedido.setItensPedidos(itensDoPedido);

        pedido.setValorTotal(calcularValorTotal(itensDoPedido));

        pedido = pedidoRepository.save(pedido);

        PedidoResponseDTO pedidoResponseDTO = new PedidoResponseDTO(pedido);
        return EntityModel.of(pedidoResponseDTO, getAllLinks(pedidoResponseDTO));
    }










    private Pedido montarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setDataPedido(LocalDate.now());
        pedido.setObservacoes(dto.observacoes());
        pedido.setStatus(dto.status());
        return pedido;
    }

    private Cliente buscarCliente(Long idCliente) {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    private List<ItemPedido> montarItensPedido(Pedido pedido, List<ItemPedidoRequestDTO> itensDTO) {
        List<ItemPedido> itens = new ArrayList<>();
        for (ItemPedidoRequestDTO itemDTO : itensDTO) {
            Produto produto = produtoRepository.findById(itemDTO.idProduto())
                    .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setQtdItem(itemDTO.qtdItem());
            item.setPrecoUnitario(produto.getPreco());
            item.setPedido(pedido);
            item.setPrecoTotal(produto.getPreco().multiply(BigDecimal.valueOf(itemDTO.qtdItem())));
            itens.add(item);
        }
        return itens;
    }

    private BigDecimal calcularValorTotal(List<ItemPedido> itens) {
        return itens.stream()
                .map(ItemPedido::getPrecoTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Collection<Link> getAllLinks(PedidoResponseDTO dto) {
        return List.of(
                linkTo(methodOn(PedidoController.class).inserirNovoPedido(null, null))
                        .withRel("create")
                        .withType("POST")
        );
    }
}
