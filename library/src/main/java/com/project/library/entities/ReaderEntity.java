package com.project.library.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "app", name = "readers")
public class ReaderEntity {
    @Id
    @EqualsAndHashCode.Include
    private UUID uuid;
    @EqualsAndHashCode.Include
    private String name;
    @Column(name = "dt_birth")
    @EqualsAndHashCode.Include
    private LocalDate dateBirth;
    @EqualsAndHashCode.Include
    private String address;
    @Column(name = "dt_registration")
    private LocalDateTime dateRegistration;
    @Version
    @Column(name = "dt_update")
    private LocalDateTime dateUpdate;
    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private Set<BookEntity> books;

    public void addBook(BookEntity book) {
        if(this.books == null) {
          this.books = new HashSet<>();
        }
        this.books.add(book);
        book.setReader(this);
    }

    public void removeBook(BookEntity book) {
        books.remove(book);
        book.setReader(null);
    }

}
