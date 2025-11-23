package com.projeto.api.repository;

import com.projeto.api.models.PlayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayListRepository extends JpaRepository<PlayList, String>, JpaSpecificationExecutor<PlayList> {
}
