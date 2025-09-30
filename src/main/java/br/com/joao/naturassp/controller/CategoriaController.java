package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.CategoriaRequestDTO;
import br.com.joao.naturassp.dto.request.CategoriaUpdateRequesteDTO;
import br.com.joao.naturassp.dto.response.CategoriaResponseDTO;
import br.com.joao.naturassp.service.impl.CategoriaServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")

public class CategoriaController {

    private final CategoriaServiceImpl categoriaService;

    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }


    @PostMapping
    public ResponseEntity<EntityModel<CategoriaResponseDTO>> adicionarCategoria(@RequestBody @Valid  CategoriaRequestDTO requestDTO, UriComponentsBuilder uriBuilder) {
        EntityModel<CategoriaResponseDTO> categoriaResponse = categoriaService.inserirNovaCategoria(requestDTO);
        var uri = uriBuilder.path("/categoria/{id}").buildAndExpand(categoriaResponse.getContent().id()).toUri();
        return ResponseEntity.created(uri).body(categoriaResponse);
    }


    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<CategoriaResponseDTO>>> listarTodasCategoria(Pageable pageable) {
        var categoria = categoriaService.recuperarTodasCategoria(pageable);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CategoriaResponseDTO>> buscarCategoriaId(@PathVariable Long id) {
        var categoria = categoriaService.bucarCategoriaId(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping("/search")
    public ResponseEntity<PagedModel<EntityModel<CategoriaResponseDTO>>> recuperarPorPalavrasChaves(@RequestParam(name="key") String palavraChave, Pageable pageable) {
        var categoria = categoriaService.recuperarPorPalavrasChaves(pageable, palavraChave);
        return ResponseEntity.ok().body(categoria);
    }




    @PutMapping
    public ResponseEntity<EntityModel<CategoriaResponseDTO>> alterarCategoria(@RequestBody @Valid CategoriaUpdateRequesteDTO requesteDTO) {
        var categoria = categoriaService.alterarCategoria(requesteDTO);
        return ResponseEntity.ok().body(categoria);
    }
}
