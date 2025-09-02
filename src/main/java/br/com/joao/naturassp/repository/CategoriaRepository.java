package br.com.joao.naturassp.repository;

import br.com.joao.naturassp.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByNomeContaining(String palavra);

    Boolean existsByNomeIgnoreCase(String nome);

    Page<Categoria> findByNomeContainingIgnoreCase(String palavraChave, Pageable pageable);
}
