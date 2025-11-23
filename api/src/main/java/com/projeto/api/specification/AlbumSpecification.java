package com.projeto.api.specification;

import com.projeto.api.dtos.AlbumDTOs.AlbumFilter;
import com.projeto.api.models.Album;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class AlbumSpecification {

    public static Specification<Album> filtrosAplicados(AlbumFilter filtros) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (StringUtils.hasText(filtros.id())) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("id"), filtros.id()));
            }
            if (StringUtils.hasText(filtros.nome())) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("nome")), "%" + filtros.nome().toLowerCase() + "%"));
            }
            if (filtros.total_faixas() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("total_faixas"), filtros.total_faixas()));
            }
            if (filtros.lancamento() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("lancamento"), filtros.lancamento()));
            }
            if (StringUtils.hasText(filtros.gravadora())) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("gravadora")), "%" + filtros.gravadora().toLowerCase() + "%"));
            }
            if (StringUtils.hasText(filtros.perfil_spotify())) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("perfil_spotify")), "%" + filtros.perfil_spotify().toLowerCase() + "%"));
            }
            if (filtros.popularidade() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("popularidade"), filtros.popularidade()));
            }
            if (filtros.nota_media() != null) {
                predicates = criteriaBuilder.and(predicates, criteriaBuilder.equal(root.get("nota_media"), filtros.nota_media()));
            }

            return predicates;
        };
    }





}
