package com.projeto.api.service;

import com.projeto.api.dtos.UsuarioDTOs.RegistrarDTO;
import com.projeto.api.dtos.UsuarioDTOs.*;
import com.projeto.api.exception.exceptions.*;
import com.projeto.api.mapper.dtos.AlbumMapper;
import com.projeto.api.mapper.dtos.UsuarioMapper;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import com.projeto.api.specification.CamposValidos;
import com.projeto.api.specification.UsuarioSpecification;
import org.springframework.data.jpa.domain.Specification;
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
import java.util.Set;


@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final PasswordEncoder password_encoder;
    private final UsuarioMapper usuarioMapper;
    private final AlbumMapper albumMapper;

    public UsuarioService(AuthenticationManager authenticationManager, UsuarioRepository usuarioRepository, TokenService tokenService, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper, AlbumMapper albumMapper) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.tokenService = tokenService;
        this.password_encoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
        this.albumMapper = albumMapper;
    }

    //Retornar todos
    public Page<UsuarioDTO> getAllUsuarios(Map<String, String> filtros, Pageable pageable) {
        // Validação dos campos de filtro
        Set<String> camposValidos = CamposValidos.USUARIO.getCampos();

        for (String campo : filtros.keySet()) {
            if (!camposValidos.contains(campo)) {
                throw new illegalfilterException("Campo de filtro inválido: " + campo);
            }
        }

        Specification<Usuario> specification = UsuarioSpecification.aplicarFiltros(filtros);
        Page<Usuario> usuarios = usuarioRepository.findAll(specification,pageable);
        if (usuarios.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum usuario encontrado");
        }
        return usuarios.map(usuarioMapper::usuarioToUsuarioDTO);
    }

    //Retornar por id

    public UsuarioDTO getUsuarioById(String id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(UsuarioNotFoundException::new);
        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    //novo usuario

    public UsuarioDTO registerUsuario(RegistrarDTO data){
        if (this.usuarioRepository.findByEmail(data.email()).isPresent()){
            throw new EmailAlreadyExistsException("E-mail ja cadastrado");
        }

        String senha = password_encoder.encode(data.senha());
        Usuario usuario = new Usuario(data.nome(), data.email(), senha, data.role());

        this.usuarioRepository.save(usuario);


        return usuarioMapper.usuarioToUsuarioDTO(usuario);
    }

    //logar

    public LoginDTO loginUsuario(AuthenticationDTO data){

        var authToken = new UsernamePasswordAuthenticationToken(data.email(), data.senha());

        try {
            var auth = authenticationManager.authenticate(authToken);
            Usuario usuario = (Usuario) auth.getPrincipal();
            UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);

            var token = tokenService.generateToken(usuarioDTO);

            return new LoginDTO(token);

        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Credenciais inválidas");
        }
    }

    //mudar senha

    public void changeSenha(String id, SenhaNovaDTO data){

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
        if (!id.equals(usuarioLogado.getId())) {
            throw new CredentialsInvalidException("Você só pode alterar sua própria senha");
        }


        // Atuaiza a senha
        String senhaNova = password_encoder.encode(data.novasenha());
        usuarioLogado.setSenha(senhaNova);
        usuarioLogado.setUltima_atualizacao_senha(LocalDateTime.now());

        usuarioRepository.save(usuarioLogado);

    }

    // trocar email
    public void changeEmail(String id, EmailNovoDTO data){

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(data.email(), data.senha())
            );
        } catch (BadCredentialsException e) {
            throw new CredentialsInvalidException("Senha incorreta");
        }

        // Verifica se o novo e-mail já está em uso
        if (usuarioRepository.findByEmail(data.email_novo()).isPresent()) {
            throw new EmailAlreadyExistsException("E-mail já está em uso");
        }


        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();


        if(!id.equals(usuarioLogado.getId())) {
            throw new CredentialsInvalidException("Você só pode alterar seu próprio e-mail");
        }


        // Atualiza e-mail
        usuarioLogado.setEmail(data.email_novo());
        usuarioLogado.setUltima_atualizacao_email(LocalDateTime.now());
        this.usuarioRepository.save(usuarioLogado);


    }

    //trocar nome

    public void changeNome(String id, UsuarioNomeDTO data){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!id.equals(usuarioLogado.getId())) {
            throw new CredentialsInvalidException("Você só pode alterar seu próprio nome");
        }


        usuarioLogado.setNome(data.nome());
        this.usuarioRepository.save(usuarioLogado);

    }

    //deletar usuario

    public void deleteUsuario(String id){

        // pega usuario logado
        var auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuarioLogado = (Usuario) auth.getPrincipal();

        if (!id.equals(usuarioLogado.getId())) {
            throw new CredentialsInvalidException("Você só pode deletar sua própria conta");
        }

        usuarioRepository.delete(usuarioLogado);
    }

}
