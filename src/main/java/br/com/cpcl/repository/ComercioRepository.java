package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpcl.entity.Comercio;

public interface ComercioRepository extends JpaRepository<Comercio, Long>{
    
}
