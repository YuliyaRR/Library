package com.project.library.core.exceptions;

import com.project.library.core.dto.error.ErrorCode;
import lombok.Getter;

public class InvalidInputServiceMultiException extends IllegalArgumentException{
    private String field;
    @Getter
    private ErrorCode errorCode;

    public InvalidInputServiceMultiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public InvalidInputServiceMultiException(String s, String field) {
        super(s);
        this.field = field;
    }

    @Override
    public String getLocalizedMessage() {
        return field;
    }
}
