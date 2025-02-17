package com.engdic_app_dictionary.service.api;

import com.engdic_app_dictionary.service.dto.Word;
import java.util.*;

public interface Dictionary{
	void putInDictionary(Word word);
	void deleteFromDictionary(Word word);
	void showElemFromDictionary(ArrayList<Word> word);
}