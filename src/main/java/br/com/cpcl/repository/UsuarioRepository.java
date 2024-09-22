package br.com.cpcl.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID>{
    
    Optional<Usuario> findByEmail(String email);
}
