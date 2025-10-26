package com.projeto.api.service;

import com.projeto.api.dtos.UsuarioDTOs.RegistrarDTO;
import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder password_encoder;

    public UsuarioService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.password_encoder = passwordEncoder;
    }

    //Retornar todos
    public List<UsuarioDTO> GetAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }

    //novo usuario

    public ResponseEntity<String> RegistrarUsuario(RegistrarDTO data){
        if (this.usuarioRepository.findByEmail(data.email()) != null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário encontrado com este e-mail");
        }

        String password_encoder = this.password_encoder.encode(data.senha());
        Usuario usuario = new Usuario(data.nome(),data.email(), password_encoder,data.role());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().body("Usuario registrado com sucesso");
    }

    //logar

    public ResponseEntity LoginUsuario(AuthenticationDTO data){

        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);

        Usuario usuario = (Usuario) auth.getPrincipal();
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);


        var token = tokenService.generateToken(usuarioDTO);

        return ResponseEntity.ok(new LoginDTO(token));

    }

    //mudar senha

    public ResponseEntity<String> MudarSenha(SenhaNovaDTO data){
        // Autentica usuário com senha antiga
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(), data.senha())
        );

        // Pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // Atuaiza a senha
        String senhaNova = password_encoder.encode(data.novasenha());
        usuarioLogado.setSenha(senhaNova);
        usuarioLogado.setUltima_atualizacao_senha(LocalDateTime.now());

        usuarioRepository.save(usuarioLogado);

        return ResponseEntity.ok("Senha atualizada com sucesso");
    }

    // trocar email
    public ResponseEntity<String> MudarEmail(EmailNovoDTO data){

        // Autentica usuário com senha
        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.email(), data.senha())
        );

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        // Atualiza e-mail
        usuarioLogado.setEmail(data.email_novo());
        usuarioLogado.setUltima_atualizacao_email(LocalDateTime.now());
        this.usuarioRepository.save(usuarioLogado);

        return ResponseEntity.ok().body("Usuario atualizado com sucesso");

    }

    //trocar nome

    public ResponseEntity<String> MudarNome(UsuarioDTO data){

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha())
        );

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();


        usuarioLogado.setNome(data.getNome());
        this.usuarioRepository.save(usuarioLogado);

        return ResponseEntity.ok().body("Usuario atualizado com sucesso");

    }

    //deletar usuario

    public ResponseEntity<String> DeletarUsuario(UsuarioDTO data){

        this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha())
        );

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        usuarioRepository.delete(usuarioLogado);

        return ResponseEntity.ok().body("Usuario deletado com sucesso");
    }

}
