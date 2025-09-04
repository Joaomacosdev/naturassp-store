package br.com.joao.naturassp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbl_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Long id;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "observacoes", length = 500)
    private String observacoes;

    @Column(name = "status")
    private Integer status;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("pedido")
    private List<ItemPedido> itensPedidos;

    public Pedido() {
    }

    public Long getId() {
        return id;
    }

    public Pedido setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public Pedido setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
        return this;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Pedido setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
        return this;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public Pedido setObservacoes(String observacoes) {
        this.observacoes = observacoes;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Pedido setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Pedido setCliente(Cliente cliente) {
        this.cliente = cliente;
        return this;
    }

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public Pedido setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pedido pedido = (Pedido) object;
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
