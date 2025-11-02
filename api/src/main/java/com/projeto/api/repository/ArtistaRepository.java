package com.projeto.api.repository;

import com.projeto.api.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, String> {

    Optional<Artista> findByNome(String nome);


}
