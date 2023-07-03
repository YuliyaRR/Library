package com.project.library.repository.api;

import com.project.library.entities.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface ReaderRepository extends JpaRepository<ReaderEntity, UUID> {
    boolean existsByNameAndDateBirthAndAddress(String name, LocalDate dateBirth, String address);
}
