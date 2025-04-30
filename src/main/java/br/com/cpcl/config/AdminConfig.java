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

            Produto produto1 = new Produto(null, "Notebook Dell", "Notebook Dell Inspiron 15", ProdutoCategory.NOTEBOOK, new BigDecimal("3500.00"), 10, "urlImagem1", comercio);
            Produto produto2 = new Produto(null, "Memória RAM 8GB", "Memória RAM DDR4 8GB", ProdutoCategory.MEMORIA, new BigDecimal("300.00"), 50, "urlImagem2", comercio);
            Produto produto3 = new Produto(null, "Monitor LG", "Monitor LG 24 polegadas", ProdutoCategory.MONITOR, new BigDecimal("800.00"), 20, "urlImagem3", comercio);

            produtoRepository.save(produto1);
            produtoRepository.save(produto2);
            produtoRepository.save(produto3);

    }
    
}
