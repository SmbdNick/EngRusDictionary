package com.dictionary.service.exception;

public class WordValidationException extends RuntimeException {
    public WordValidationException(String message) {
        super(message);
    }
}
