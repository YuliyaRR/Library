package com.project.library.service.api;

import com.project.library.core.dto.BookOrder;
import com.project.library.core.dto.ReaderInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface IReaderService {
    void createReader(@NotNull @Valid ReaderInfo readerInfo);
    List<ReaderInfo> getAll();
    ReaderInfo getReader(@NotNull UUID uuid);
    void updateReaderInfo(@NotNull UUID uuid, @NotNull @Past LocalDateTime dateUpdate, @NotNull @Valid ReaderInfo readerInfo);
    void deleteReader(@NotNull UUID uuid);
    void writeBooksToReader(@NotNull @Valid BookOrder bookOrder);
    void deleteReturnedBooksFromReader(@NotNull @Valid BookOrder bookOrder);
}
