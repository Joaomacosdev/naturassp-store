package br.com.joao.naturassp.model;

import br.com.joao.naturassp.dto.request.CategoriaRequestDTO;
import br.com.joao.naturassp.dto.request.CategoriaUpdateRequesteDTO;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tbl_categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Long id;
    @Column(name = "nome_categoria", length = 100, nullable = false, unique = true)
    private String nome;


    public Categoria() {
    }

    public Categoria(CategoriaRequestDTO requestDTO) {
        this.nome = requestDTO.nome();
    }

    public void atualizar(CategoriaUpdateRequesteDTO requestDTO) {
        if (nome != null) {
            this.nome = requestDTO.nome();
        }
    }

    public Long getId() {
        return id;
    }

    public Categoria setId(Long id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Categoria setNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Categoria categoria = (Categoria) object;
        return Objects.equals(getId(), categoria.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
