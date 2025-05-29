package com.dictionary.dao.impl;

import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.CreateWord;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class SqlDictionary implements Dictionary {
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
    public void deleteWord(String word) throws IOException {

    }
}
