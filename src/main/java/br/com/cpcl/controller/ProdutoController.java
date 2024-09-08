package br.com.cpcl.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/produtos")
public class ProdutoController {
    
    @Autowired
    private ProdutoService service;

    @Autowired
    private ComercioService comercioService;


    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        var produtos = service.findAll();
        return ResponseEntity.ok().body(produtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Produto>> getAll(@PathVariable Long id){
        var produto = service.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
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

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Optional<Produto>> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
