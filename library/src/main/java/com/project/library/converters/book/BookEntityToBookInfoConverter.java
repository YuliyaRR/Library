package com.project.library.converters.book;

import com.project.library.core.dto.AuthorInfo;
import com.project.library.core.dto.BookInfo;
import com.project.library.entities.BookEntity;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookEntityToBookInfoConverter implements Converter<BookEntity, BookInfo> {
    @Override
    public BookInfo convert(BookEntity source) {
        return BookInfo.builder()
                .setUuid(source.getUuid())
                .setTitle(source.getTitle())
                .setAuthor(new AuthorInfo(source.getAuthor().getName()))
                .setPublicationYear(source.getPublicationYear())
                .setGenre(source.getGenre().getGenre())
                .setDtCreate(source.getDateCreate())
                .setDtUpdate(source.getDateUpdate())
                .build();
    }
}
