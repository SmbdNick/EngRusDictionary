package com.engdic_app_dictionary.service.impl;

import com.engdic_app_dictionary.service.dto.Word;
import com.engdic_app_dictionary.service.api.Dictionary;
import java.util.*;

class InMemoryDictionary implements Dictionary {
	HashMap<String, ArrayList<String>> engrusDictionary = new HashMap<String, ArrayList<String>>();
	
	public void putInDictionary(Word word){
		//Place-Holder 
		System.out.println("Метод, позволяющий добавить слово/перевод в словарь");
	}
	
	public void deleteFromDictionary(Word word){
		//Place-Holder 
		System.out.println("Метод, позволяющий удалить слово/перевод из словаря");
	}
	
	public void showElemFromDictionary(ArrayList<Word> word){
		//Place-Holder 
		System.out.println("Метод, позволяющий вывести слова/перевод из словаря");
	}
	
}

class Main{
	public static void main(String[] args){
		InMemoryDictionary dic = new InMemoryDictionary();
		
		ArrayList<String> wordPut = new ArrayList<String>(
			Arrays.asList("Плотва", "Таракан"));
		Word w = new Word("Roach", wordPut);
		ArrayList<Word> wo = new ArrayList<Word>(Arrays.asList(w));
		
		dic.putInDictionary(w);
		dic.deleteFromDictionary(w);
		dic.showElemFromDictionary(wo);
	}
}