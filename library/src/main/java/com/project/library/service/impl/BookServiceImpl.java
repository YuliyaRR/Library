package com.project.library.service.impl;

import com.project.library.core.dto.BookInfo;
import com.project.library.core.dto.error.ErrorCode;
import com.project.library.core.exceptions.ConversionTimeException;
import com.project.library.core.exceptions.NotFoundDataBaseException;
import com.project.library.entities.AuthorEntity;
import com.project.library.entities.BookEntity;
import com.project.library.entities.GenreEntity;
import com.project.library.repository.api.BookRepository;
import com.project.library.service.api.IAuthorService;
import com.project.library.service.api.IBookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@Service
public class BookServiceImpl implements IBookService {
    private final BookRepository repository;
    private final ConversionService conversionService;
    private final IAuthorService authorService;

    @Override
    @Transactional
    public void createBook(@NotNull @Valid BookInfo bookInfo) {
        if (!conversionService.canConvert(BookInfo.class, BookEntity.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        AuthorEntity authorEntity;
        String name = bookInfo.getAuthor().getName();

        try {
            authorEntity = authorService.findByName(name);
        } catch (NotFoundDataBaseException e) {
            authorEntity = new AuthorEntity(UUID.randomUUID(), name);
        }

        BookEntity bookEntity = conversionService.convert(bookInfo, BookEntity.class);
        bookEntity.setAuthor(authorEntity);
        repository.save(bookEntity);
    }

    @Override
    public List<BookInfo> getAll() {
        if (!conversionService.canConvert(BookEntity.class, BookInfo.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        return repository.findAll().stream()
                .map(entity -> conversionService.convert(entity, BookInfo.class))
                .collect(Collectors.toList());
    }

    @Override
    public BookInfo getBook(@NotNull UUID uuid) {
        BookEntity bookEntity = getBookEntity(uuid);

        if (!conversionService.canConvert(BookEntity.class, BookInfo.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        return conversionService.convert(bookEntity, BookInfo.class);
    }

    @Override
    @Transactional
    public void updateBook(@NotNull UUID uuid, @NotNull @Past LocalDateTime dateUpdate, @NotNull @Valid BookInfo bookInfo) {
        BookEntity bookEntity = getBookEntity(uuid);

        if(bookEntity.getDateUpdate().equals(dateUpdate)) {
            AuthorEntity author = bookEntity.getAuthor();
            String nameAuthor = bookInfo.getAuthor().getName();

            if(!author.getName().equals(nameAuthor)){
                bookEntity.setAuthor(new AuthorEntity(UUID.randomUUID(), nameAuthor));
            }

            bookEntity.setTitle(bookInfo.getTitle());
            bookEntity.setPublicationYear(bookInfo.getPublicationYear());
            bookEntity.setGenre(new GenreEntity(bookInfo.getGenre()));

            repository.save(bookEntity);

        } else {
            throw new NotFoundDataBaseException("Book with this version was not found in the database", ErrorCode.ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteBook(@NotNull UUID uuid) {
        repository.delete(getBookEntity(uuid));
    }

    @Override
    public BookEntity getBookEntity(@NotNull UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new NotFoundDataBaseException("The book wasn't found in the database", ErrorCode.ERROR));
    }
}
