package com.dictionary.service.validator.impl;

import com.dictionary.service.validator.api.Validator;

public class EngValidator implements Validator {
    private static final String ENGLISH_ALPHABET = "^[a-zA-Z]*$";

    @Override
    public String getPattern() {
        return ENGLISH_ALPHABET;
    }
}
