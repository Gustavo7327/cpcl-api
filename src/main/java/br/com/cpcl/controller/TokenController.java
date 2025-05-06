package br.com.cpcl.controller;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.cpcl.dto.LoginRequest;
import br.com.cpcl.dto.LoginResponse;
import br.com.cpcl.dto.UserRegister;
import br.com.cpcl.entity.Role;
import br.com.cpcl.service.UsuarioService;

@RestController
public class TokenController {
    
    private final JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsuarioService usuarioService;

    public TokenController(JwtEncoder jwtEncoder, BCryptPasswordEncoder bCryptPasswordEncoder, UsuarioService usuarioService) {
        this.jwtEncoder = jwtEncoder;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.usuarioService = usuarioService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){

        var usuario = usuarioService.getUser(loginRequest.email());

        if(usuario.isEmpty() || !usuario.get().isLoginCorreto(loginRequest, bCryptPasswordEncoder)){
            throw new BadCredentialsException("Usuario ou senha inválido!");
        }

        var now = Instant.now();
        var tempoExpiracao = 604800L;

        var scopes = usuario.get().getRoles().stream().map(Role::getName).collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
            .issuer("cpcl")
            .subject(usuario.get().getEmail())
            .issuedAt(now)
            .expiresAt(now.plusSeconds(tempoExpiracao))
            .claim("scope", scopes)
            .build();

        var token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return ResponseEntity.ok(new LoginResponse(token, tempoExpiracao));

    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegister registerRequest){

        if (usuarioService.getUser(registerRequest.email()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um usuário cadastrado com esse e-mail.");         
        }

        if (registerRequest.senha() == null || registerRequest.senha().isEmpty() || registerRequest.email() == null || registerRequest.email().isEmpty() || registerRequest.nome() == null || registerRequest.nome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todos os campos são obrigatórios.");
        }
        
        usuarioService.createUser(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
