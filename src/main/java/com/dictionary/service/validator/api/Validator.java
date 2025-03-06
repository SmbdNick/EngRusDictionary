package com.dictionary.service.validator.api;

import com.dictionary.service.exception.ValidationException;

public interface Validator {
    default void validate(String word){
        if (word == null || word.isEmpty()) {
            throw new ValidationException("Word cannot be empty");
        }
        if(!(word.matches(getPattern()))) {
            throw new ValidationException("Word must contain only corresponding language's letters");
        }

    }
    String getPattern();

}
