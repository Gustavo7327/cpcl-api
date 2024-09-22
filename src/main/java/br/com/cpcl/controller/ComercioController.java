package br.com.cpcl.controller;

import java.time.LocalDate;
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

import br.com.cpcl.dto.ComercioRequest;
import br.com.cpcl.entity.Comercio;
import br.com.cpcl.service.ComercioService;

@RestController
@RequestMapping(value = "/comercios")
public class ComercioController {

    @Autowired
    private ComercioService service;
    
    
    @GetMapping
    public ResponseEntity<List<Comercio>> getAll(){
        var comercios = service.findAll();
        return ResponseEntity.ok().body(comercios);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Comercio>> getAll(@PathVariable Long id){
        var comercio = service.findById(id);
        return ResponseEntity.ok().body(comercio);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_COMERCIANTE')")
    public ResponseEntity<Void> create(@RequestBody ComercioRequest request){
        Comercio comercio = new Comercio();
        comercio.setNomeFantasia(request.nomeFantasia());
        comercio.setEndereco(request.endereco());
        comercio.setHorarioFuncionamento(request.horarioFuncionamento());
        comercio.setDataCadastro(LocalDate.now());
        service.create(comercio);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Optional<Comercio>> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
