package com.projeto.api.repository;

import com.projeto.api.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

public interface MusicaRepository extends JpaRepository<Musica, String>, JpaSpecificationExecutor<Musica> {
}
