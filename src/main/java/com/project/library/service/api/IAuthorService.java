package com.project.library.service.api;

import com.project.library.entities.AuthorEntity;
import com.project.library.validator.annotations.ValidString;

public interface IAuthorService {
    AuthorEntity findByName(@ValidString String name);
}
