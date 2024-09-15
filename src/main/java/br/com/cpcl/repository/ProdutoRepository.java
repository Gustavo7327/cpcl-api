package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
}
