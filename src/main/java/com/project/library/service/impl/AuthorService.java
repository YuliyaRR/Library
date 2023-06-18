package com.project.library.service.impl;

import com.project.library.core.dto.error.ErrorCode;
import com.project.library.core.exceptions.NotFoundDataBaseException;
import com.project.library.entities.AuthorEntity;
import com.project.library.repository.api.AuthorRepository;
import com.project.library.service.api.IAuthorService;
import com.project.library.validator.annotations.ValidString;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Validated
@RequiredArgsConstructor
@Service
public class AuthorService implements IAuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorEntity findByName(@ValidString String name) {
        Optional<AuthorEntity> byName = authorRepository.findByName(name);

        if (byName.isPresent()) {
            return byName.get();
        } else {
            throw new NotFoundDataBaseException("Author with this name wasn't found in the database", ErrorCode.ERROR);
        }
    }
}
