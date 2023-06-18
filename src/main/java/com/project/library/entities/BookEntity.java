package com.project.library.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "app", name = "books")
public class BookEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID uuid;
    @EqualsAndHashCode.Include
    private String title;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_uuid")
    @EqualsAndHashCode.Include
    private AuthorEntity author;
    @Column(name = "publication_year")
    @EqualsAndHashCode.Include
    private int publicationYear;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "genre")
    @EqualsAndHashCode.Include
    private GenreEntity genre;
    @Column(name = "dt_create")
    private LocalDateTime dateCreate;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
    @ManyToOne(fetch = FetchType.LAZY)
    private ReaderEntity reader;
}
