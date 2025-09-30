package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.controller.CategoriaController;
import br.com.joao.naturassp.dto.request.CategoriaRequestDTO;
import br.com.joao.naturassp.dto.request.CategoriaUpdateRequesteDTO;
import br.com.joao.naturassp.dto.response.CategoriaResponseDTO;
import br.com.joao.naturassp.infra.exception.BadRequestException;
import br.com.joao.naturassp.infra.exception.DuplicateResourceException;
import br.com.joao.naturassp.infra.exception.NotFoundException;
import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.repository.CategoriaRepository;
import br.com.joao.naturassp.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final PagedResourcesAssembler<CategoriaResponseDTO> assembler;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, PagedResourcesAssembler<CategoriaResponseDTO> assembler) {
        this.categoriaRepository = categoriaRepository;
        this.assembler = assembler;
    }

    @Transactional
    @Override
    public EntityModel<CategoriaResponseDTO> inserirNovaCategoria(CategoriaRequestDTO requestDTO) {

        validarCadastroCategoria( null, requestDTO);


        var categoria = categoriaRepository.save(new Categoria(requestDTO));
        var dto = new CategoriaResponseDTO(categoria);
        var links = getAllLinks(dto, Pageable.unpaged());
        return EntityModel.of(dto, links);
    }

    @Transactional
    @Override
    public EntityModel<CategoriaResponseDTO> alterarCategoria(CategoriaUpdateRequesteDTO requestDTO) {
        var categoria = categoriaRepository.findById(requestDTO.id())
                .orElseThrow(() -> new NotFoundException("Categoria com o id:" + requestDTO.id() + "não encontrado"));

        validarCadastroCategoria(requestDTO, null);
        categoria.atualizar(requestDTO);

        var dto = new CategoriaResponseDTO(categoria);
        var links = getAllLinks(dto, Pageable.unpaged());
        return EntityModel.of(dto, links);
    }

    @Transactional(readOnly = true)
    @Override
    public EntityModel<CategoriaResponseDTO> bucarCategoriaId(Long id) {
        var categoria = categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException("Categoria não encontrada"));

        var dto = new CategoriaResponseDTO(categoria);
        var links = getAllLinks(dto, Pageable.unpaged());
        return EntityModel.of(dto, links);
    }


    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<CategoriaResponseDTO>> recuperarTodasCategoria(@PageableDefault(size = 10, sort = "nome") Pageable pageable) {
        Page<CategoriaResponseDTO> page = categoriaRepository.findAll(pageable).map(CategoriaResponseDTO::new);
        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    @Transactional(readOnly = true)
    @Override
    public PagedModel<EntityModel<CategoriaResponseDTO>> recuperarPorPalavrasChaves(
            @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC)
            Pageable pageable,
            String palavraChave) {

        if (palavraChave == null) {
            throw new BadRequestException("Palavra chave deve ser inserida");
        }

        Page<CategoriaResponseDTO> page = categoriaRepository.findByNomeContainingIgnoreCase(palavraChave, pageable)
                .map(CategoriaResponseDTO::new);

        return assembler.toModel(page, dto -> EntityModel.of(dto, getAllLinks(dto, pageable)));
    }

    private void validarCadastroCategoria(CategoriaUpdateRequesteDTO updateDTO, CategoriaRequestDTO requestDTO){
        if (requestDTO.nome() == null || requestDTO.nome().isEmpty()) {
            throw new BadRequestException("Nome deve ser preenchido");
        }

        if (categoriaRepository.existsByNomeIgnoreCase(requestDTO.nome())) {
            throw new DuplicateResourceException("Já existe uma categoria com esse nome");
        }
    }

    private Collection<Link> getAllLinks(CategoriaResponseDTO dto, Pageable pageable) {
        return List.of(
                linkTo(methodOn(CategoriaController.class).adicionarCategoria(null, null)).withRel("create").withType("POST"),
                linkTo(methodOn(CategoriaController.class).listarTodasCategoria(pageable)).withRel("listAll").withType("GET"),
                linkTo(methodOn(CategoriaController.class).recuperarPorPalavrasChaves(dto.nome(), pageable)).withRel("searchByName").withType("GET"),
                linkTo(methodOn(CategoriaController.class).alterarCategoria(null)).withRel("update").withType("PUT")
        );
    }
}
