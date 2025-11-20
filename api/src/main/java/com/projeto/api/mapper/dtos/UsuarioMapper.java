package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.PlaylistDTOs.PlayListResumoDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioDTO;
import com.projeto.api.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
    ReviewResumoDTO usuarioToReviewResumoDTO(Usuario usuario);
    PlayListResumoDTO usuarioToPlayListResumoDTO(Usuario usuario);
}
