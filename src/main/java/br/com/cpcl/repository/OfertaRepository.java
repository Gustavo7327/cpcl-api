package br.com.cpcl.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cpcl.entity.Oferta;

public interface OfertaRepository extends JpaRepository<Oferta, Long> {

    @Query("SELECT o FROM Oferta o WHERE o.comercio.id = :comercioId")
    List<Oferta> findByComercioId(@Param("comercioId") Long comercioId);
}
