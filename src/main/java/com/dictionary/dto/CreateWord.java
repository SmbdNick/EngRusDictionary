package com.dictionary.dto;
import java.util.*;

public class CreateWord {
	private final String key;
	private final List<String> values;
	
	public CreateWord(String key, List<String> values){
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public List<String> getValues() {
		return values;
	}
}