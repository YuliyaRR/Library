package com.project.library.converters.book;

import com.project.library.core.dto.BookInfo;
import com.project.library.entities.BookEntity;
import com.project.library.entities.GenreEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class BookInfoToBookEntityConverter implements Converter<BookInfo, BookEntity> {

    @Override
    public BookEntity convert(BookInfo source) {
        LocalDateTime now = LocalDateTime.now();

        return BookEntity.builder()
                .setUuid(UUID.randomUUID())
                .setTitle(source.getTitle())
                .setPublicationYear(source.getPublicationYear())
                .setGenre(new GenreEntity(source.getGenre()))
                .setDateCreate(now)
                .setDateUpdate(now)
                .build();
    }
}
