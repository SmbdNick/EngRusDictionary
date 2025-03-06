package com.dictionary.service;

import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.CreateWord;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.service.validator.api.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class DictionaryService {
    private final Dictionary dictionary;
    private final Validator keyValidator;
    private final Validator valueValidator;


    private static final String SUCCESS_CREATE = "%s created successfully";

    public DictionaryService(Dictionary dictionary, Validator keyValidator, Validator valueValidator) {
        this.dictionary = dictionary;
        this.keyValidator = keyValidator;
        this.valueValidator = valueValidator;
    }

    public String createWord(String key, List<String> values) {
        keyValidator.validate(key);

        values.forEach(value -> valueValidator.validate(value));

        CreateWord newWord = new CreateWord(key, values);
        dictionary.modify(newWord);

        return String.format(SUCCESS_CREATE, "word");
    }

    public Optional<GetWord> getWordByKey(String key) {
        return dictionary.get(key);
    }

    public Set<Word> getAllWordsByKeyList(final List<String> keyList) {
        Set<Word> result = new HashSet<>();

        for (Word word : dictionary.getAllWords()) {
            String key = word.getKey();
            if (keyList.contains(key)) {
                result.add(new Word(key, word.getValues()));
            }
        }

        return result;
    }

    public Set<Word> getAllWords() {
        Set<Word> result = new HashSet<>();

        dictionary.getAllWords().forEach(word -> {
            result.add(new Word(word.getKey(), word.getValues()));
        });

        return result;
    }

    public void deleteEntryByKey(String key) {
        dictionary.delete(key);
    }
}
