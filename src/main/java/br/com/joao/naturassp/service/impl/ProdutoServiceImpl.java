package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.controller.ProdutoController;
import br.com.joao.naturassp.dto.request.ProdutoRequestDTO;
import br.com.joao.naturassp.dto.request.ProdutoUpdateDTO;
import br.com.joao.naturassp.dto.response.ProdutoResponseDTO;
import br.com.joao.naturassp.infra.exception.BadRequestException;
import br.com.joao.naturassp.infra.exception.DuplicateResourceException;
import br.com.joao.naturassp.infra.exception.NotFoundException;
import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.model.Produto;
import br.com.joao.naturassp.repository.CategoriaRepository;
import br.com.joao.naturassp.repository.ProdutoRepository;
import br.com.joao.naturassp.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PagedResourcesAssembler<ProdutoResponseDTO> assembler;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, PagedResourcesAssembler<ProdutoResponseDTO> assembler) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.assembler = assembler;
    }

    @Transactional(readOnly = false)
    @Override
    public EntityModel<ProdutoResponseDTO> inserirNovoProduto(ProdutoRequestDTO requestDTO) {

        if (requestDTO.nome() == null || requestDTO.nome().isEmpty()) {
            throw new BadRequestException("Nome deve ser preenchido");
        }

        if (produtoRepository.existsByNomeIgnoreCase(requestDTO.nome().trim())) {
            throw new DuplicateResourceException("Já existe um produto com esse nome");
        }

        var produto = produtoRepository.save(new Produto(requestDTO));
        var dto = new ProdutoResponseDTO(produto);
        var links = getAllLinks(dto, Pageable.unpaged());
        return EntityModel.of(dto, links);
    }


    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarTodosProdutos(Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findAll(pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarDestques(int destaque, Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findAllByDestaque( 1,pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public EntityModel<ProdutoResponseDTO> recuperarPorId(Long id) {

        var produto = produtoRepository.findById(id).orElseThrow(() -> new NotFoundException("produto não econtrado"));

        var dto = new ProdutoResponseDTO(produto);
        var links = getAllLinks(dto, Pageable.unpaged());

        return EntityModel.of(dto, links);
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarDisponiveis(Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findAllByDisponivelTrue(pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarPorCategoria(Long idCategoria, Pageable pageable) {
        verificarIdCategoria(idCategoria);

        Page<ProdutoResponseDTO> page = produtoRepository.findAllByCategoriaId(idCategoria, pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));

    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> listarProdutosIndisponivel(Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findAllByDisponivelFalse(pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> findAllByDisponivelAndCategoria(Boolean disponivel, Categoria cat, Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findAllByDisponivelAndCategoria(disponivel, cat, pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<ProdutoResponseDTO>> buscarPorPalavrasChave(String keyNome, Pageable pageable) {
        Page<ProdutoResponseDTO> page = produtoRepository.findByNomeContainingOrDetalheContaining(keyNome, keyNome,pageable).map(ProdutoResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }


    @Transactional(readOnly = false)
    @Override
    public EntityModel<ProdutoResponseDTO> alterarProduto(Long id, ProdutoUpdateDTO updateDTO) {

        var produto = produtoRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Produto com Id: " + updateDTO.id() + "Não encontrado")
        );

        if (updateDTO.nome() == null || updateDTO.nome().isEmpty()) {
            throw new BadRequestException("Nome deve ser preenchido");
        }

        if (produtoRepository.existsByNomeIgnoreCase(updateDTO.nome().trim())
                && !produto.getId().equals(id)) {
            throw new DuplicateResourceException("Já existe um produto com esse nome");
        }
        produto.atualizar(updateDTO);
        var dto = new ProdutoResponseDTO(produto);
        var links = getAllLinks(dto, Pageable.unpaged());

        return EntityModel.of(dto, links);

    }

    private void verificarIdCategoria(Long idCategoria) {
        if (idCategoria == null) {
            throw new IllegalArgumentException("O ID da categoria não pode ser nulo.");
        }

        if (!categoriaRepository.existsById(idCategoria)) {
            throw new NotFoundException("O Id " + idCategoria + " não existe");
        }
    }


    private Collection<Link> getAllLinks(ProdutoResponseDTO dto, Pageable pageable) {
        return List.of(
                linkTo(methodOn(ProdutoController.class).novoProduto(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(ProdutoController.class).recuperarPorId(dto.idProduto())).withRel("listAll").withType("GET"),
                linkTo(methodOn(ProdutoController.class).listarTodos(pageable)).withRel("listAll").withType("GET"),
                linkTo(methodOn(ProdutoController.class).recuperarPorCategoria(dto.categoria().getId(), pageable)).withRel("listAll").withType("GET"),
                linkTo(methodOn(ProdutoController.class).listarDisponiveis(pageable)).withRel("listAllTrue").withType("GET"),
                linkTo(methodOn(ProdutoController.class).listarProdutosIndisponivel(pageable)).withRel("listAllFalse").withType("GET"),
                linkTo(methodOn(ProdutoController.class).findAllByDisponivelAndCategoria(dto.disponivel(), dto.categoria(), pageable)).withRel("listAllFalse").withType("GET")


        );
    }

}
