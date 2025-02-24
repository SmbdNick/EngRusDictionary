package com.dictionary.service.validator.impl;

import com.dictionary.service.exception.ValidationException;
import com.dictionary.service.validator.api.Validator;

public class JapValidator implements Validator {
    @Override
    public void validate(String word) {

        if (word == null || word.isEmpty()) {
            throw new ValidationException("Word cannot be empty");
        }

        for (char c : word.toCharArray()) {
            if (Character.UnicodeBlock.of(c) != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                    || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.HIRAGANA
                    || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.KATAKANA
                    || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                    || Character.UnicodeBlock.of(c) != Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
                throw new ValidationException("Word must contain only Japanese characters");
            }
        }
    }
}
