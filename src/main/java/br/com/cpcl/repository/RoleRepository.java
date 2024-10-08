package br.com.cpcl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cpcl.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

    Role findByName(String nome);
    
}
