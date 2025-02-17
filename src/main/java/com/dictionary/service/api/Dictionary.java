package com.dictionary.service.api;

import com.dictionary.service.dto.CreateWord;
import com.dictionary.service.dto.GetWord;
import com.dictionary.service.model.Word;

import java.util.*;

public interface Dictionary{
	void modify(final CreateWord createWord);
	Optional<GetWord> get(final String word);
	Set<Word> getAllWords();
	void delete(final String word);
	Set<Word> getAllWordsByKeyList(final List<String> keyList);
}