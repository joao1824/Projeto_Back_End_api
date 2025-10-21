package com.projeto.api.service;

import com.projeto.api.dtos.UsuarioDTO;
import com.projeto.api.models.Usuario;
import com.projeto.api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    //Retornar todos
    public List<UsuarioDTO> GetAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Nenhum Usuários encontrado");
        }

        return usuarios.stream().map(UsuarioDTO::new).collect(Collectors.toList());
    }









}
