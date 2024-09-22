package br.com.cpcl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpcl.dto.ProdutoRequest;
import br.com.cpcl.entity.Comercio;
import br.com.cpcl.entity.Produto;
import br.com.cpcl.service.ComercioService;
import br.com.cpcl.service.ProdutoService;

@RestController
@RequestMapping(value = "/comercios")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @Autowired
    private ComercioService comercioService;


    @GetMapping(value = "/{comercioId}/produtos")
    public ResponseEntity<List<Produto>> getAll(@PathVariable Long comercioId){

        var comercio = comercioService.findById(comercioId);

        if(comercio.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        var produtos = service.findAllByComercio(comercio.get());
        return ResponseEntity.ok().body(produtos);
    }


    @GetMapping(value = "/{comercioId}/produto/{produtoId}")
    public ResponseEntity<Optional<Produto>> getAll(@PathVariable Long comercioId, @PathVariable Long produtoId){
        var produto = service.findById(produtoId);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping(value = "/produto")
    @PreAuthorize("hasAuthority('SCOPE_COMERCIANTE')")
    public ResponseEntity<Void> create(@RequestBody ProdutoRequest request){
        Produto produto = new Produto();
        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setCategoria(request.categoria());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        produto.setUrlImagem(request.urlImagem());
        Comercio comercio = comercioService.findById(request.comercio_id()).get();
        produto.setComercio(comercio);
        service.create(produto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/produto/{id}")
    @PreAuthorize("hasAuthority('SCOPE_COMERCIANTE')")
    public ResponseEntity<Optional<Produto>> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
