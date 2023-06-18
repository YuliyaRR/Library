package com.project.library.entities;

import com.project.library.core.dto.Genre;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "app", name = "genres")
public class GenreEntity {
    @Id
    @Enumerated(EnumType.STRING)
    private Genre genre;
}
