package com.projeto.api.repository;

import com.projeto.api.models.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayListRepository extends JpaRepository<PlayList, String> {
}
