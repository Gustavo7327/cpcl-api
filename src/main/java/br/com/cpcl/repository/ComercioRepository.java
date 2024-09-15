package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Comercio;

@Repository
public interface ComercioRepository extends JpaRepository<Comercio, Long>{
    
}
