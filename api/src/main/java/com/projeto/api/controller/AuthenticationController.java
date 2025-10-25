package com.projeto.api.controller;

import com.projeto.api.dtos.*;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;
    private final PasswordEncoder password_encoder;


    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder password_encoder) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.password_encoder = password_encoder;
    }

    //logar

    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);

        Usuario usuario = (Usuario) auth.getPrincipal();
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);


        var token = tokenService.generateToken(usuarioDTO);

        return ResponseEntity.ok(new LoginDTO(token));
    }

    //registrar

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegistrarDTO data){
        if (this.usuarioRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String password_encoder = this.password_encoder.encode(data.senha());
        Usuario usuario = new Usuario(data.nome(),data.email(), password_encoder,data.role());

        //Isso tem que sair daqui depois e ir para um service é provisório
        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    //mudar senha

    @PostMapping("/trocar-senha")
    public ResponseEntity mudarSenha(@RequestBody @Valid SenhaNovaDTO data){
        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);

        Usuario usuario = usuarioRepository.findUsuarioByEmail(data.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        String senhaNova = this.password_encoder.encode(data.novasenha());
        usuario.setSenha(senhaNova);
        usuario.setUltima_atualizacao_senha(LocalDateTime.now());
        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }




}
