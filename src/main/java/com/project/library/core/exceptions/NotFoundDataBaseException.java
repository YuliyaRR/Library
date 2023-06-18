package com.project.library.core.exceptions;

import com.project.library.core.dto.error.ErrorCode;
import lombok.Getter;

@Getter
public class NotFoundDataBaseException extends RuntimeException {
    private ErrorCode errorCode;

    public NotFoundDataBaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}

