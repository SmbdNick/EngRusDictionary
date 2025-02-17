package com.dictionary.service.dto;

import java.util.List;

public class GetWord {
    private final List<String> values;

    public GetWord(List<String> values){
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
}
