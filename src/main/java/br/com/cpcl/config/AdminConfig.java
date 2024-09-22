package br.com.cpcl.config;

import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpcl.entity.Role;
import br.com.cpcl.entity.Usuario;
import br.com.cpcl.repository.RoleRepository;
import br.com.cpcl.repository.UsuarioRepository;

@Configuration
public class AdminConfig implements CommandLineRunner{

    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public AdminConfig(RoleRepository roleRepository, UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var usuarioAdmin = usuarioRepository.findByEmail("admin@gmail.com");

        usuarioAdmin.ifPresentOrElse(
            usuario -> {
                System.out.println("Usuário admin já existe");
            },
            () -> {
                var usuario = new Usuario();
                usuario.setEmail("admin@gmail.com");
                usuario.setNome("Administrador teste");
                usuario.setSenha(passwordEncoder.encode("admin123"));
                usuario.setRoles(Set.of(roleAdmin));
                usuarioRepository.save(usuario);
            }
        );
    }
    
}
