package com.projeto.api.repository;

import com.projeto.api.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, String>, JpaSpecificationExecutor<Artista> {

    Optional<Artista> findByNome(String nome);


}
