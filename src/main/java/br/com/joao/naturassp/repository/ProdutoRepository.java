package br.com.joao.naturassp.repository;

import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public Page<Produto> findAllByCategoriaId(Long id, Pageable pageable);
    public Page<Produto> findAllByDisponivelTrue(Pageable pageable);
    public Page<Produto> findAllByDisponivelAndCategoria(Boolean disponivel, Categoria cat, Pageable pageable);
    public Boolean existsByNomeIgnoreCase(String nome);
    public Boolean existsByCategoriaId(Long Id);
    public Page<Produto> findAllByDestaque(int destaque ,Pageable pageable);

    public Page<Produto> findAllByDisponivelFalse( Pageable pageable);
    public Page<Produto> findByNomeContainingOrDetalheContaining(String keyNome, String keyDetalhe,Pageable pageable);
}
