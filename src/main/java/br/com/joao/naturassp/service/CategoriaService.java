package br.com.joao.naturassp.service;

import br.com.joao.naturassp.dto.request.CategoriaRequestDTO;
import br.com.joao.naturassp.dto.request.CategoriaUpdateRequesteDTO;
import br.com.joao.naturassp.dto.response.CategoriaResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface CategoriaService {

    // Este método recebe uma categoria só com o nome preechindo e vai inserir no banco
    public EntityModel<CategoriaResponseDTO> inserirNovaCategoria(CategoriaRequestDTO categoria);

    // Este método vai alterar a categoria existente e retorna-la se o update deu certo e nul se deu errado
    public EntityModel<CategoriaResponseDTO> alterarCategoria(CategoriaUpdateRequesteDTO requestDTO);

    // Este método retorna todas as categorias sem filtro
    public PagedModel<EntityModel<CategoriaResponseDTO>> recuperarTodasCategoria(Pageable pageable);

    // Este método retorna todas as categorias por palavra chave
    public PagedModel<EntityModel<CategoriaResponseDTO>> recuperarPorPalavrasChaves(Pageable pageable ,String palavraChave);
}
