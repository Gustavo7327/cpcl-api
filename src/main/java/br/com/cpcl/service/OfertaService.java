package br.com.cpcl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpcl.entity.Oferta;
import br.com.cpcl.repository.OfertaRepository;

@Service
public class OfertaService {
    
    @Autowired
    private OfertaRepository repository;

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Oferta findById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Iterable<Oferta> findAll(){
        return repository.findAll();
    }

    public Iterable<Oferta> findByComercioId(Long id){
        return repository.findByComercioId(id);
    }

    public void create(Oferta oferta){
        repository.save(oferta);
    }
}
