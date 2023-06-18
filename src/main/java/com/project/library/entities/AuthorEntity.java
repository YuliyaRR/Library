package com.project.library.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "app", name = "authors")
public class AuthorEntity {
    @Id
    private UUID uuid;
    private String name;
}
