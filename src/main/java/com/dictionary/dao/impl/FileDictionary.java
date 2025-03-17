package com.dictionary.dao.impl;

import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.CreateWord;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class FileDictionary implements Dictionary {
    private final Set<Word> dictionary = new HashSet<>();
    @Override
    public void modify(CreateWord createWord) {

    }

    @Override
    public Optional<GetWord> get(String word) {
        return Optional.empty();
    }

    @Override
    public Set<Word> getAllWords() {
        return null;
    }

    @Override
    public void delete(String word) {

    }
}
