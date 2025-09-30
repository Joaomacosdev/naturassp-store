package br.com.joao.naturassp.service;

import br.com.joao.naturassp.dto.request.ProdutoRequestDTO;
import br.com.joao.naturassp.dto.request.ProdutoUpdateDTO;
import br.com.joao.naturassp.dto.response.ProdutoResponseDTO;
import br.com.joao.naturassp.model.Categoria;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

import java.util.List;

public interface ProdutoService {

    public EntityModel<ProdutoResponseDTO> inserirNovoProduto(ProdutoRequestDTO requestDTO);

    public EntityModel<ProdutoResponseDTO> alterarProduto(Long id ,ProdutoUpdateDTO updateDTO);

    public EntityModel<ProdutoResponseDTO> recuperarPorId(Long id);

    public PagedModel<EntityModel<ProdutoResponseDTO>> listarTodosProdutos(Pageable pageable);
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarDestques(int destaque ,Pageable pageable);


    public PagedModel<EntityModel<ProdutoResponseDTO>> listarDisponiveis(Pageable pageable);

    public PagedModel<EntityModel<ProdutoResponseDTO>> listarPorCategoria(Long idCategoria, Pageable pageable);

    public PagedModel<EntityModel<ProdutoResponseDTO>> listarProdutosIndisponivel(Pageable pageable);

    public PagedModel<EntityModel<ProdutoResponseDTO>> findAllByDisponivelAndCategoria(Boolean disponivel, Categoria cat, Pageable pageable);

    public  PagedModel<EntityModel<ProdutoResponseDTO>> buscarPorPalavrasChave(String keyNome, Pageable pageable);
}
