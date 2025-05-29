package com.dictionary.dto;

import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreateWord {
    private final String key;
    List<@Nullable String> list;
    private final List<String> values;

    public CreateWord(String key, List<String> values) {
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