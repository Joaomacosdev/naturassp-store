package br.com.joao.naturassp.model;

import br.com.joao.naturassp.dto.request.ItemPedidoRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tbl_item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_seq")
    private Long numSeq;
    @Column(name = "qtd_item")
    private Integer qtdItem;
    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;
    @Column(name = "preco_total")
    private BigDecimal precoTotal;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    @JsonIgnoreProperties("itensPedidos")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    public ItemPedido() {
    }

    public ItemPedido(ItemPedidoRequestDTO itemPedidoRequestDTO) {
        this.qtdItem = itemPedidoRequestDTO.qtdItem();
        this.precoUnitario = itemPedidoRequestDTO.precoUnitario();
    }

    public void calcularPrecoTotal() {
        this.precoTotal = precoUnitario.multiply(BigDecimal.valueOf(qtdItem));
    }


    public Long getNumSeq() {
        return numSeq;
    }

    public ItemPedido setNumSeq(Long numSeq) {
        this.numSeq = numSeq;
        return this;
    }

    public Integer getQtdItem() {
        return qtdItem;
    }

    public ItemPedido setQtdItem(Integer qtdItem) {
        this.qtdItem = qtdItem;
        return this;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public ItemPedido setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
        return this;
    }

    public BigDecimal getPrecoTotal() {
        return precoTotal;
    }

    public ItemPedido setPrecoTotal(BigDecimal precoTotal) {
        this.precoTotal = precoTotal;
        return this;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public ItemPedido setPedido(Pedido pedido) {
        this.pedido = pedido;
        return this;
    }

    public Produto getProduto() {
        return produto;
    }

    public ItemPedido setProduto(Produto produto) {
        this.produto = produto;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ItemPedido that = (ItemPedido) object;
        return Objects.equals(getNumSeq(), that.getNumSeq());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getNumSeq());
    }
}
