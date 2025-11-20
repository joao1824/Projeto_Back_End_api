package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.AlbumDTOs.AlbumResumoDTO;
import com.projeto.api.dtos.ReviewDTOs.ReviewDTO;
import com.projeto.api.dtos.TagDTOs.TagResumoDTO;
import com.projeto.api.dtos.UsuarioDTOs.UsuarioResumoDTO;
import com.projeto.api.models.Album;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import com.projeto.api.models.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewDTO toReviewDTO(Review review);
    AlbumResumoDTO toAlbumResumoDTO(Album album);
    TagResumoDTO toTagResumoDTO(Tag tag);
    UsuarioResumoDTO toUsuarioResumoDTO(Usuario usuario);
}
