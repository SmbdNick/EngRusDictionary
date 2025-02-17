package com.dictionary.service.model;

import java.util.List;

public class Word {
    private final String key;
    private final List<String> values;

    public Word(String key, List<String> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public List<String> getValues() {
        return values;
    }
}
