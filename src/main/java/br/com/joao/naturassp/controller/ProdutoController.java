package br.com.joao.naturassp.controller;

import br.com.joao.naturassp.dto.request.ProdutoRequestDTO;
import br.com.joao.naturassp.dto.request.ProdutoUpdateDTO;
import br.com.joao.naturassp.dto.response.ProdutoResponseDTO;
import br.com.joao.naturassp.dto.response.UploadFileResponseDTO;
import br.com.joao.naturassp.model.Categoria;
import br.com.joao.naturassp.service.impl.ProdutoServiceImpl;
import br.com.joao.naturassp.service.impl.FileStorageServiceImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoServiceImpl produtoService;
    private final FileStorageServiceImpl fileStorageService;

    public ProdutoController(ProdutoServiceImpl produtoService, FileStorageServiceImpl fileStorageService) {
        this.produtoService = produtoService;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping()
    public ResponseEntity<EntityModel<ProdutoResponseDTO>> novoProduto(@RequestBody @Valid ProdutoRequestDTO dto, UriComponentsBuilder uriBuilder) {
        EntityModel<ProdutoResponseDTO> produtoResponse = produtoService.inserirNovoProduto(dto);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produtoResponse.getContent().id()).toUri();
        return ResponseEntity.created(uri).body(produtoResponse);
    }

    @PostMapping("/uploadFile")
    public ResponseEntity<UploadFileResponseDTO> uploadFile(@RequestParam("file") MultipartFile file) {
        var fileName = fileStorageService.storeFile(file);
        var fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/file/v1/downloadFile")
                .path(fileName)
                .toUriString();
        return ResponseEntity.ok().body(new UploadFileResponseDTO(fileName, fileDownloadUri, file.getContentType(), file.getSize()));
    }


    @GetMapping()
    public ResponseEntity<PagedModel<EntityModel<ProdutoResponseDTO>>> listarTodos(Pageable pageable) {
        var produto = produtoService.listarTodosProdutos(pageable);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/categoria/{id}")
    public ResponseEntity<PagedModel<EntityModel<ProdutoResponseDTO>>> recuperarPorCategoria(@PathVariable(name = "id") Long idCateg, Pageable pageable) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(idCateg, pageable));
    }

    @GetMapping("/disponivel")
    public ResponseEntity<PagedModel<EntityModel<ProdutoResponseDTO>>> listarDisponiveis(Pageable pageable) {
        var produto = produtoService.listarDisponiveis(pageable);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/indisponiveis")
    public ResponseEntity<PagedModel<EntityModel<ProdutoResponseDTO>>> listarProdutosIndisponivel(Pageable pageable) {
        var produto = produtoService.listarProdutosIndisponivel(pageable);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/filtro")
    public ResponseEntity<PagedModel<EntityModel<ProdutoResponseDTO>>> findAllByDisponivelAndCategoria(@RequestParam Boolean disponivel, @RequestParam Categoria cat, Pageable pageable) {
        var produto = produtoService.findAllByDisponivelAndCategoria(disponivel, cat, pageable);
        return ResponseEntity.ok().body(produto);
    }

    @PutMapping
    public ResponseEntity<EntityModel<ProdutoResponseDTO>> alterarProduto(@RequestBody @Valid ProdutoUpdateDTO updateDTO){
        var produto = produtoService.alterarProduto(updateDTO);
        return ResponseEntity.ok().body(produto);
    }

}
