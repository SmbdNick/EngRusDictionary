package com.dictionary.service.validator.impl;

import com.dictionary.service.validator.api.Validator;

public class RusValidator implements Validator {
    private static final String RUSSIAN_ALPHABET = "^[а-яА-ЯЁё]*$";

    @Override
    public String getPattern() {
        return RUSSIAN_ALPHABET;
    }
}
