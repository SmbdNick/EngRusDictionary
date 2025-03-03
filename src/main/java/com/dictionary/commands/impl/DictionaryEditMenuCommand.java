package com.dictionary.commands.impl;

import com.dictionary.commands.api.Command;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.service.DictionaryService;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.*;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;
import static com.dictionary.ui.impl.ConsoleUi.*;

public class DictionaryEditMenuCommand implements Command {
    String currentDictionaryKey = getCurrentDictionaryKey();
    Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
    @Override
    public void doCommand(UIState uiState, Console console, Scanner scanner) {
        uiState = UIState.DICTIONARY_EDIT_MENU;


        say(currentDictionaryKey + " dictionary selected\n" +
                "Select what you want to do\n" +
                "1. Show entry by key\n" +
                "2. Show all entries\n" +
                "3. Add entry to dictionary\n" +
                "4. Remove entry from dictionary\n" +
                "5. Back to Main Menu");

        switch (ask(console, scanner)) {
            case "1":
                say("Enter a key to search to");
                showWord(ask(console, scanner));
                setUIState(uiState);
                break;

            case "2":
                showAllWords();
                setUIState(uiState);
                break;

            case "3":
                say("Enter your dictionary entry using format: key-value1/value2/...");
                addWord(ask(console, scanner));
                setUIState(uiState);
                break;

            case "4":
                say("Enter a Key to delete an entry");
                deleteWord(ask(console, scanner));
                setUIState(uiState);
                break;
            case "5":
                uiState = UIState.MAIN_MENU;
                setUIState(uiState);
                break;
        }

    }

    private void showWord(String key) {
        Optional<GetWord> word = dictionaryServiceMap.get(currentDictionaryKey)
                .getWordByKey(key);
        word.ifPresent(w -> say(key + " " + w.toString()));
        if (word.isEmpty()) {
            say("Word not found");
        }
    }
    private void showAllWords() {
        List<Word> wordList = new ArrayList<>(dictionaryServiceMap.get(currentDictionaryKey).getAllWords());
        for (Word word: wordList){
            say(word.getKey() + " " + word.getValues());
        }
    }
    public void addWord(String entry) {
        String key;
        String[] keyPlusValue = entry.split("-");
        key = keyPlusValue[0];
        say(key);

        List<String> values = List.of(keyPlusValue[1].split("/"));
        dictionaryServiceMap.get(currentDictionaryKey).createWord(key, values);
        setDictionaryMap(dictionaryServiceMap);
    }
    private void deleteWord(String key) {
        dictionaryServiceMap.get(currentDictionaryKey).deleteEntryByKey(key);
    }
}
