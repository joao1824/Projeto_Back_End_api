package com.projeto.api.repository;

import com.projeto.api.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
    boolean existsByNome(String nome);
}
