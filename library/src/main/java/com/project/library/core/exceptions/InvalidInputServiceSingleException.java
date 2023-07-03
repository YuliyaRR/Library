package com.project.library.core.exceptions;

import com.project.library.core.dto.error.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidInputServiceSingleException extends IllegalArgumentException{
    private ErrorCode errorCode;

    public InvalidInputServiceSingleException(String s, ErrorCode errorCode) {
        super(s);
        this.errorCode = errorCode;
    }
}
