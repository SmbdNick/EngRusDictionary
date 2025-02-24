package com.dictionary.service.validator.impl;

import com.dictionary.service.exception.ValidationException;
import com.dictionary.service.validator.api.Validator;

public class RusValidator implements Validator {
    private static final String RUSSIAN_ALPHABET = "[а-яА-ЯЁё]+";


    @Override
    public void validate(String word) {
        if (word == null || word.isEmpty()) {
            throw new ValidationException("Word cannot be empty");
        }

        if(!(word.matches(RUSSIAN_ALPHABET))) {
            throw new ValidationException("Word must contain only Russian letters");
        }
    }
}
