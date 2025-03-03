package com.dictionary.dto;

import java.util.List;

public class GetWord {
    private final List<String> values;

    public GetWord(List<String> values){
        this.values = values;
    }

    public List<String> getValues() {
        return values;
    }
//TODO перенести в UI
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        values.forEach(value -> s.append(value).append(" "));
        return s.toString();
    }
}
