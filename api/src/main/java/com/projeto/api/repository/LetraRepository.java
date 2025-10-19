package com.projeto.api.repository;

import com.projeto.api.models.Letra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetraRepository extends JpaRepository<Letra, String> {
}
