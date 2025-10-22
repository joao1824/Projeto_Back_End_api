package com.projeto.api.controller;

import com.projeto.api.dtos.AuthenticationDTO;
import com.projeto.api.dtos.LoginDTO;
import com.projeto.api.dtos.RegistrarDTO;
import com.projeto.api.dtos.UsuarioDTO;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;


    public AuthenticationController(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
    }


    @PostMapping("/logar")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        var usernameSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(usernameSenha);

        Usuario usuario = (Usuario) auth.getPrincipal();
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);


        var token = tokenService.generateToken(usuarioDTO);

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/registrar")
    public ResponseEntity register(@RequestBody @Valid RegistrarDTO data){
        if (this.usuarioRepository.findByEmail(data.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encrypedPassoword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario usuario = new Usuario(data.nome(),data.email(), encrypedPassoword,data.role());

        //Isso tem que sair daqui depois e ir para um service é provisório
        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }


}
