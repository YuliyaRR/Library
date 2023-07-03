package com.project.library.service.api;

import com.project.library.core.dto.BookInfo;
import com.project.library.entities.BookEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IBookService {
    void createBook(@NotNull @Valid BookInfo bookInfo);
    List<BookInfo> getAll();
    BookInfo getBook(@NotNull UUID uuid);
    void updateBook(@NotNull UUID uuid, @NotNull @Past LocalDateTime dateUpdate, @NotNull @Valid BookInfo bookInfo);
    void deleteBook(@NotNull UUID uuid);
    BookEntity getBookEntity(@NotNull UUID uuid);
}
