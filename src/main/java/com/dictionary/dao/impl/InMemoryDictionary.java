package com.dictionary.dao.impl;

import com.dictionary.dto.CreateWord;
import com.dictionary.dao.api.Dictionary;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;

import java.util.*;

public class InMemoryDictionary implements Dictionary {
	private final Set<Word> dictionary;

	public InMemoryDictionary(Set<Word> dictionary) {
		this.dictionary = dictionary;
	}

	@Override
	public void modify(final CreateWord editWord) {
        List<String> values = new ArrayList<>(editWord.getValues());

		get(editWord.getKey()).
				ifPresent(word -> values.addAll(word.getValues()));

		delete(editWord.getKey());
		add(new CreateWord(editWord.getKey(), values));
	}

	@Override
	public Optional<GetWord> get(final String key) {
		for (Word word : dictionary) {
			if (word.getKey().equals(key)) {
				return Optional.of(new GetWord(word.getValues()));
			}
		}

		return Optional.empty();
	}

	@Override
	public Set<Word> getAllWords() {
		return dictionary;
	}

	@Override
	public void delete(final String word){
		if (isWordExist(word)) {
			dictionary.remove(getWordByIndex(getIndexByWord(word)));
		}
	}

	private void add(CreateWord word){
		dictionary.add(new Word(word.getKey(), word.getValues()));
	}

	private boolean isWordExist(String word) {
        return !dictionary.isEmpty() && getIndexByWord(word) != -1;
    }

	private Word getWordByIndex(int index) {
		return dictionary.toArray(new Word[0])[index];
	}

	private int getIndexByWord(String word) {
		for (int i = 0; i < dictionary.size(); i++) {
			if (dictionary.toArray(new Word[0])[i].getKey().equals(word)) {
				return i;
			}
		}

		return -1;
	}
}