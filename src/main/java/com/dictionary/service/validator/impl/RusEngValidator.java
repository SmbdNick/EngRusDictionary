package com.dictionary.service.validator.impl;

import com.dictionary.service.exception.WordValidationException;
import com.dictionary.service.validator.api.Validator;

public class RusEngValidator implements Validator {
    private static final String RUSSIAN_ALPHABET = "^[а-яА-Я]$";

    @Override
    public void validate(String word) {
        if (word == null || word.isEmpty()) {
            throw new WordValidationException("Word cannot be empty");
        }

        if(!word.contains(RUSSIAN_ALPHABET)) {
            throw new WordValidationException("Word must contain only Russian letters");
        }
    }
}
