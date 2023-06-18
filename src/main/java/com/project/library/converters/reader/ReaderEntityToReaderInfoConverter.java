package com.project.library.converters.reader;

import com.project.library.converters.book.BookEntityToBookInfoConverter;
import com.project.library.core.dto.BookInfo;
import com.project.library.core.dto.ReaderInfo;
import com.project.library.entities.BookEntity;
import com.project.library.entities.ReaderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class ReaderEntityToReaderInfoConverter implements Converter<ReaderEntity, ReaderInfo> {
    private final BookEntityToBookInfoConverter bookConverter;

    @Override
    public ReaderInfo convert(ReaderEntity source) {
        Set<BookEntity> books = source.getBooks();

        List<BookInfo> booksInfo = books.stream()
                .map(entity -> bookConverter.convert(entity))
                .toList();

        return ReaderInfo.builder()
                .setUuid(source.getUuid())
                .setName(source.getName())
                .setDateBirth(source.getDateBirth())
                .setAddress(source.getAddress())
                .setBooks(booksInfo)
                .setDtCreate(source.getDateRegistration())
                .setDtUpdate(source.getDateUpdate())
                .build();
    }
}
