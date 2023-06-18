package com.project.library.repository.api;

import com.project.library.entities.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository extends JpaRepository<AuthorEntity, UUID> {

    Optional<AuthorEntity> findByName(String name);
}
