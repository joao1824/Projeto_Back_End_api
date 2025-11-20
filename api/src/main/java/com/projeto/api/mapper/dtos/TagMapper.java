package com.projeto.api.mapper.dtos;

import com.projeto.api.dtos.ReviewDTOs.ReviewResumoDTO;
import com.projeto.api.dtos.TagDTOs.TagDTO;
import com.projeto.api.models.Review;
import com.projeto.api.models.Tag;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDTO tagToTagDTO(Tag tag);
    ReviewResumoDTO reviewResumoDTO(Review review);
}
