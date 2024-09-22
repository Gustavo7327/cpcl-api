package br.com.cpcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

    List<Produto> findAllByComercioId(Long comercioId);
    
}
