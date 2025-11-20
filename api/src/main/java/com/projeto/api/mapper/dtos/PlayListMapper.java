package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.MusicaDTOs.MusicaResumoDTO;
import com.projeto.api.dtos.PlaylistDTOs.PlayListDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.Musica;
import com.projeto.api.models.PlayList;
import com.projeto.api.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayListMapper {
    PlayListDTO toPlayListDTO(PlayList playlist);
    MusicaResumoDTO toMusicaResumoDTO(Musica musica);
    UsuarioResumoDTO toUsuarioResumoDTO(Usuario usuario);
}
