package br.com.joao.naturassp.model;

import br.com.joao.naturassp.dto.request.ProdutoRequestDTO;
import br.com.joao.naturassp.dto.request.ProdutoUpdateDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tbl_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto", nullable = false, unique = true)
    private Long id;
    @Column(name = "nome_produto", length = 100, nullable = false)
    private String nome;
    @Column(name = "detalhe_produto", length = 500)
    private String detalhe;
    @Column(name = "link_foto", length = 255, nullable = false)
    private String linkFoto;

    @Column(name = "preco_produto", nullable = false)
    private BigDecimal preco;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @Column(name = "disponivel")
    private Boolean disponivel;

    public Produto() {
    }


    public Produto(ProdutoRequestDTO requestDTO) {
        this.nome = requestDTO.nome();
        this.detalhe = requestDTO.detalhe();
        this.linkFoto = requestDTO.linkFoto();
        this.preco = requestDTO.preco();
        this.categoria = requestDTO.categoria();
        this.disponivel = true;
    }

    public void atualizar(ProdutoUpdateDTO updateDTO) {
        if (nome != null) {
            this.nome = updateDTO.nome();
        }
        if (detalhe != null) {
            this.detalhe = updateDTO.detalhe();
        }
        if (linkFoto != null) {
            this.linkFoto = updateDTO.linkFoto();
        }
        if (preco != null) {
            this.preco = updateDTO.preco();
        }
        if (categoria != null) {
            this.categoria = updateDTO.categoria();
        }
        if (disponivel != null) {
            this.disponivel = updateDTO.disponivel();
        }
    }

    public Long getId() {
        return id;
    }

    public Produto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Produto setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public Produto setDetalhe(String detalhe) {
        this.detalhe = detalhe;
        return this;
    }

    public String getLinkFoto() {
        return linkFoto;
    }

    public Produto setLinkFoto(String linkFoto) {
        this.linkFoto = linkFoto;
        return this;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Produto setPreco(BigDecimal preco) {
        this.preco = preco;
        return this;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Produto setCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public Produto setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Produto produto = (Produto) object;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", detalhe='" + detalhe + '\'' +
                ", linkFoto='" + linkFoto + '\'' +
                ", preco=" + preco +
                ", categoria=" + categoria +
                '}';
    }


}
