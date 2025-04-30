package br.com.cpcl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpcl.entity.Oferta;
import br.com.cpcl.service.OfertaService;

@RestController
@RequestMapping("/ofertas")
public class OfertaController {
    
    @Autowired
    private OfertaService ofertaService;

    @GetMapping
    public ResponseEntity<Iterable<Oferta>> findAll(){
        Iterable<Oferta> ofertas = ofertaService.findAll();
        if (ofertas.iterator().hasNext()) {
            return ResponseEntity.ok(ofertas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/comercio/{id}")
    public ResponseEntity<Iterable<Oferta>> findByComercioId(Long id){
        Iterable<Oferta> ofertas = ofertaService.findByComercioId(id);
        if (ofertas.iterator().hasNext()) {
            return ResponseEntity.ok(ofertas);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Oferta> findById(Long id){
        Oferta oferta = ofertaService.findById(id);
        if (oferta != null) {
            return ResponseEntity.ok(oferta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/create")
    public ResponseEntity<Oferta> create(Oferta oferta){
        ofertaService.create(oferta);
        return ResponseEntity.ok(oferta);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> delete(Long id){
        ofertaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
