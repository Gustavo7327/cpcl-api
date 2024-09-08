package br.com.cpcl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpcl.entity.Comercio;
import br.com.cpcl.repository.ComercioRepository;

@Service
public class ComercioService {
    
    @Autowired
    private ComercioRepository repository;

    public List<Comercio> findAll(){
        return repository.findAll();
    }

    public Optional<Comercio> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void create(Comercio comercio){
        repository.save(comercio);
    }
}
