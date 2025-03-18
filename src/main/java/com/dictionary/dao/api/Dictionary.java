package com.dictionary.dao.api;

import com.dictionary.dto.CreateWord;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;

import java.io.IOException;
import java.util.*;

public interface Dictionary{
	void modify(final CreateWord createWord);
    Optional<GetWord> get(final String word);
	Set<Word> getAllWords();
	void deleteWord(final String word);
}