package com.dictionary.service.validator.impl;

import com.dictionary.service.exception.ValidationException;
import com.dictionary.service.validator.api.Validator;

public class EngValidator implements Validator {
    private static final String ENGLISH_ALPHABET = "^[a-zA-Z]$";
    @Override
    public void validate(String word) {
        if (word == null || word.isEmpty()) {
            throw new ValidationException("Word cannot be empty");
        }

        if(!word.contains(ENGLISH_ALPHABET)) {
            throw new ValidationException("Word must contain only English letters");
        }
    }
}
