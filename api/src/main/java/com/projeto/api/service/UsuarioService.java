package com.projeto.api.service;

import com.projeto.api.dtos.UsuarioDTOs.RegistrarDTO;
import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.exception.exceptions.CredentialsInvalidException;
import com.projeto.api.exception.exceptions.EmailAlreadyExistsException;
import com.projeto.api.mapper.dtos.UsuarioMapper;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.specification.UsuarioSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.Map;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder password_encoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.password_encoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    //Retornar todos
    public Page<UsuarioDTO> GetAllUsuarios(Map<String, String> filtros, Pageable pageable) {
        Specification<Usuario> specification = UsuarioSpecification.aplicarFiltros(filtros);
        Page<Usuario> usuarios = usuarioRepository.findAll(specification,pageable);
        return usuarios.map(usuarioMapper::usuarioToUsuarioDTO);
    }

    //novo usuario

    public ResponseEntity<String> RegistrarUsuario(RegistrarDTO data){
        if (this.usuarioRepository.findByEmail(data.email()).isPresent()){
            throw new EmailAlreadyExistsException("E-mail ja cadastrado");
        }

        String senha = password_encoder.encode(data.senha());
        Usuario usuario = new Usuario(data.nome(), data.email(), senha, data.role());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().body("Usuario registrado com sucesso");
    }

    //logar

    public ResponseEntity LoginUsuario(AuthenticationDTO data){

        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        try {
            var auth = authenticationManager.authenticate(authToken);
            Usuario usuario = (Usuario) auth.getPrincipal();
            UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);

            var token = tokenService.generateToken(usuarioDTO);

            return ResponseEntity.ok(new LoginDTO(token));

        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Credenciais inválidas");
        }
    }

    //mudar senha

    public ResponseEntity<String> MudarSenha(SenhaNovaDTO data){

        try {
            // Autentica usuário com senha antiga
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.senha())
            );
        }catch (BadCredentialsException e){
            throw new CredentialsInvalidException("Credenciais inválidas");
        }


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

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.senha())
            );
        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Senha incorreta");
        }

        // Verifica se o novo e-mail já está em uso
        if (usuarioRepository.findByEmail(data.email_novo()) != null) {
            throw new EmailAlreadyExistsException("E-mail já está em uso");
        }


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
        // autentica usuario
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha())
            );
        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Credenciais inválidas");
        }

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();


        usuarioLogado.setNome(data.getNome());
        this.usuarioRepository.save(usuarioLogado);

        return ResponseEntity.ok().body("Usuario atualizado com sucesso");

    }

    //deletar usuario

    public ResponseEntity<String> DeletarUsuario(UsuarioDTO data){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.getEmail(), data.getSenha())
            );
        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Credenciais inválidas");
        }

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        usuarioRepository.delete(usuarioLogado);

        return ResponseEntity.ok().body("Usuario deletado com sucesso");
    }

}
