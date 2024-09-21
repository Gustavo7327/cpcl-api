package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Oferta;

@Repository
public interface OfertaRepository extends JpaRepository<Oferta, Long>{
    
}
