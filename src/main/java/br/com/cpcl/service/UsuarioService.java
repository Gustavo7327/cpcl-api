package br.com.cpcl.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cpcl.dto.LoginRequest;
import br.com.cpcl.dto.UserRegister;
import br.com.cpcl.entity.Role;
import br.com.cpcl.entity.Usuario;
import br.com.cpcl.repository.RoleRepository;
import br.com.cpcl.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void createUser(UserRegister userRegister){
        var roleBasico = roleRepository.findByName(Role.Values.BASICO.name());
        Usuario usuario = new Usuario();
        usuario.setEmail(userRegister.email());
        usuario.setSenha(userRegister.senha());
        usuario.setNome(userRegister.nome());
        usuario.setRoles(Set.of(roleBasico));
        usuarioRepository.save(usuario);
    }

    public Optional<Usuario> getUser(LoginRequest loginRequest){
        return usuarioRepository.findByEmail(loginRequest.email());
    }
}
