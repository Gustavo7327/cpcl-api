package br.com.cpcl.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpcl.entity.Comercio;
import br.com.cpcl.entity.Produto;
import br.com.cpcl.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;


    public List<Produto> findAllByComercio(Comercio comercio){
        return repository.findAllByComercioId(comercio.getId());
    }

    public Optional<Produto> findById(Long id){
        return repository.findById(id);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void create(Produto produto){
        repository.save(produto);
    }
}
