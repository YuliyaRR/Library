package com.project.library.service.impl;

import com.project.library.core.dto.BookInfo;
import com.project.library.core.dto.BookOrder;
import com.project.library.core.dto.ReaderInfo;
import com.project.library.core.dto.error.ErrorCode;
import com.project.library.core.exceptions.ConversionTimeException;
import com.project.library.core.exceptions.InvalidInputServiceMultiException;
import com.project.library.core.exceptions.InvalidInputServiceSingleException;
import com.project.library.core.exceptions.NotFoundDataBaseException;
import com.project.library.entities.BookEntity;
import com.project.library.entities.ReaderEntity;
import com.project.library.repository.api.ReaderRepository;
import com.project.library.service.api.IBookService;
import com.project.library.service.api.IReaderService;
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
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@Service
public class ReaderServiceImpl implements IReaderService {
    private final ReaderRepository repository;
    private final ConversionService conversionService;
    private final IBookService bookService;

    @Override
    @Transactional
    public void createReader(@NotNull @Valid ReaderInfo readerInfo) {
        if(checkUniqueReader(readerInfo)) {
            throw new InvalidInputServiceSingleException("This reader is already registered", ErrorCode.ERROR);
        } else {

            if (!conversionService.canConvert(ReaderInfo.class, ReaderEntity.class)) {
                throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
            }

            ReaderEntity readerEntity = conversionService.convert(readerInfo, ReaderEntity.class);

            repository.save(readerEntity);
        }
    }

    @Override
    public List<ReaderInfo> getAll() {
        if (!conversionService.canConvert(ReaderEntity.class, ReaderInfo.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }
        List<ReaderEntity> all = repository.findAll();

        return all.stream()
                .map(entity -> conversionService.convert(entity, ReaderInfo.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReaderInfo getReader(@NotNull UUID uuid) {
        ReaderEntity readerEntity = getReaderEntity(uuid);
        if (!conversionService.canConvert(ReaderEntity.class, ReaderInfo.class)) {
            throw new ConversionTimeException("Unable to convert", ErrorCode.ERROR);
        }

        return conversionService.convert(readerEntity, ReaderInfo.class);
    }

    @Override
    @Transactional
    public void updateReaderInfo(@NotNull UUID uuid, @NotNull @Past LocalDateTime dateUpdate, @NotNull @Valid ReaderInfo readerInfo) {
        ReaderEntity readerEntity = getReaderEntity(uuid);

        if(readerEntity.getDateUpdate().equals(dateUpdate)) {
            readerEntity.setName(readerInfo.getName());
            readerEntity.setDateBirth(readerInfo.getDateBirth());
            readerEntity.setAddress(readerInfo.getAddress());

            repository.save(readerEntity);
        } else {
            throw new NotFoundDataBaseException("Reader with this version wasn't found in the database", ErrorCode.ERROR);
        }
    }

    @Override
    @Transactional
    public void deleteReader(@NotNull UUID uuid) {
        repository.delete(getReaderEntity(uuid));
    }

    @Override
    @Transactional(noRollbackFor = InvalidInputServiceMultiException.class)
    public void writeBooksToReader(@NotNull @Valid BookOrder bookOrder) {
        UUID uuidReader = bookOrder.getUuidReader();
        Set<BookInfo> books = bookOrder.getBooks();

        ReaderEntity readerEntity = getReaderEntity(uuidReader);

        InvalidInputServiceMultiException multiException = new InvalidInputServiceMultiException(ErrorCode.STRUCTURED_ERROR);

        for (BookInfo book : books) {
            UUID uuid = book.getUuid();

            try {
                BookEntity bookEntity = bookService.getBookEntity(uuid);

                if(isBookFree(bookEntity)) {
                    readerEntity.addBook(bookEntity);
                } else {
                    multiException.addSuppressed(new InvalidInputServiceMultiException("This book is being read by another reader. Please select another book", String.format("uuid: %s", uuid)));
                }

            } catch (NotFoundDataBaseException e) {
                multiException.addSuppressed(new InvalidInputServiceMultiException(e.getMessage(), String.format("uuid: %s", uuid)));
            }
        }

        repository.save(readerEntity);

        if(multiException.getSuppressed().length != 0) {
            throw multiException;
        }
    }

    @Override
    @Transactional(noRollbackFor = InvalidInputServiceMultiException.class)
    public void deleteReturnedBooksFromReader(@NotNull @Valid BookOrder bookOrder) {
        UUID uuidReader = bookOrder.getUuidReader();
        Set<BookInfo> books = bookOrder.getBooks();

        ReaderEntity readerEntity = getReaderEntity(uuidReader);
        Set<BookEntity> entityBooks = readerEntity.getBooks();

        InvalidInputServiceMultiException multiException = new InvalidInputServiceMultiException(ErrorCode.STRUCTURED_ERROR);

        for (BookInfo book : books) {
            UUID uuid = book.getUuid();

            boolean anyMatch = entityBooks.stream()
                    .anyMatch(entityBook -> entityBook.getUuid().equals(uuid));

            if(anyMatch) {
                BookEntity bookEntity = bookService.getBookEntity(uuid);
                readerEntity.removeBook(bookEntity);
            } else {
                multiException.addSuppressed(new InvalidInputServiceMultiException("You don't have this book", String.format("uuid: %s", uuid)));
            }
        }

        repository.save(readerEntity);

        if(multiException.getSuppressed().length != 0) {
            throw multiException;
        }

    }
    
    private ReaderEntity getReaderEntity(UUID uuid) {
        return repository.findById(uuid)
                .orElseThrow(() -> new NotFoundDataBaseException("The reader wasn't found in the database", ErrorCode.ERROR));
    }

    private boolean checkUniqueReader(ReaderInfo readerInfo) {
        return repository.existsByNameAndDateBirthAndAddress(readerInfo.getName(), readerInfo.getDateBirth(), readerInfo.getAddress());
    }

    private boolean isBookFree(BookEntity bookEntity) {
        return bookEntity.getReader() == null;
    }
}
