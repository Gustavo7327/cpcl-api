package br.com.cpcl.config;

import java.util.Set;
import java.time.LocalDate;
import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.com.cpcl.entity.Comercio;
import br.com.cpcl.entity.ComercioStatus;
import br.com.cpcl.entity.Produto;
import br.com.cpcl.entity.ProdutoCategory;
import br.com.cpcl.entity.Role;
import br.com.cpcl.entity.Usuario;
import br.com.cpcl.repository.ComercioRepository;
import br.com.cpcl.repository.ProdutoRepository;
import br.com.cpcl.repository.RoleRepository;
import br.com.cpcl.repository.UsuarioRepository;

@Configuration
public class AdminConfig implements CommandLineRunner{

    private ComercioRepository comercioRepository;
    private ProdutoRepository produtoRepository;
    private RoleRepository roleRepository;
    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;


    public AdminConfig(ComercioRepository comercioRepository, ProdutoRepository produtoRepository,RoleRepository roleRepository, UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.comercioRepository = comercioRepository;
        this.produtoRepository = produtoRepository;
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var roleAdmin = roleRepository.findByName(Role.Values.ADMIN.name());

        var roleComerciante = roleRepository.findByName(Role.Values.COMERCIANTE.name());

        var usuarioComerciante = usuarioRepository.findByEmail("comerciante@gmail.com");

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

        usuarioComerciante.ifPresentOrElse(
            usuario -> {
                System.out.println("Usuário admin já existe");
            },
            () -> {
                var usuario = new Usuario();
                usuario.setEmail("comerciante@gmail.com");
                usuario.setNome("Comerciante teste");
                usuario.setSenha(passwordEncoder.encode("comerciante123"));
                usuario.setRoles(Set.of(roleComerciante));
                usuarioRepository.save(usuario);
            }
        );

            Usuario usuario = usuarioRepository.findByEmail("comerciante@gmail.com").get();
            Comercio comercio = comercioRepository.save(new Comercio(1L, "Loja da Silva", "12345678901234", "Rua das Flores, 123", "40028922", "Segunda a Sexta: 08:00 às 18:00", LocalDate.now(), ComercioStatus.AUTORIZADO, usuario));

            produtoRepository.save(new Produto(1L, "Arroz", "Arroz 1kg branco", ProdutoCategory.MERCEARIA, BigDecimal.valueOf(4.99), 120, "https://api.google.com/image.png", comercio));

            produtoRepository.save(new Produto(2L, "Refrigerante", "0 açúcar", ProdutoCategory.BEBIDAS, BigDecimal.valueOf(7.50), 28, "https://api.google.com/image.png", comercio));

            produtoRepository.save(new Produto(3L, "Frango", "Frango 1kg temperado", ProdutoCategory.CARNES, BigDecimal.valueOf(12.99), 30, "https://api.google.com/image.png", comercio));

            produtoRepository.save(new Produto(4L, "Queijo", "Queijo 1kg Mussarela", ProdutoCategory.MERCEARIA, BigDecimal.valueOf(16.99), 14, "https://api.google.com/image.png", comercio));

            produtoRepository.save(new Produto(5L, "Banana", "Cacho de bananas", ProdutoCategory.FRUTAS, BigDecimal.valueOf(6.99), 10, "https://api.google.com/image.png", comercio));

    }
    
}
