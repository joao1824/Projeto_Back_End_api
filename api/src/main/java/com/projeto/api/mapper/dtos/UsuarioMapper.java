package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
}
