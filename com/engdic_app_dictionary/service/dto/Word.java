package com.engdic_app_dictionary.service.dto;
import java.util.*;

public class Word{
	String engWord = new String();
	ArrayList<String> rusWords = new ArrayList<String>();
	
	public Word(String eWord, ArrayList<String> ruWords){
		engWord = eWord;
		rusWords = ruWords;
	}
}