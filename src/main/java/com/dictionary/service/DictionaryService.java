package com.dictionary.service;

import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.CreateWord;
import com.dictionary.model.Word;
import com.dictionary.service.validator.api.Validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DictionaryService {
    private final Dictionary dictionary;
    private final Validator validator;

    private static final String SUCCESS_CREATE = "%s created successfully";

    public DictionaryService(Dictionary dictionary, Validator validator) {
        this.dictionary = dictionary;
        this.validator = validator;
    }

    // TODO только пример, возможно надо переделать и добавить такие же для всех методов словаря (может и больше)
    public String createWord(String key, List<String> values) {
        validator.validate(key);

        CreateWord newWord = new CreateWord(key, values);
        dictionary.modify(newWord);

        return String.format(SUCCESS_CREATE, "word");
    }

    public Set<Word> getAllWordsByKeyList(final List<String> keyList){
        Set<Word> result = new HashSet<>();

        for (Word word : dictionary.getAllWords()) {
            String key = word.getKey();
            if(keyList.contains(key)) {
                result.add(new Word(key, word.getValues()));
            }
        }

        return result;
    }
}
