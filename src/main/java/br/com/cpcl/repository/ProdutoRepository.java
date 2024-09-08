package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpcl.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
