package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.service.DictionaryService;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.*;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.*;

public class DictionaryEditMenuCommand implements Command {
    String currentDictionaryKey = getCurrentDictionaryKey();
    Map<String, DictionaryService> dictionaryServiceMap = getDictionaryMap();
    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.DICTIONARY_EDIT_MENU);


        consoleInteractions.say(currentDictionaryKey + " dictionary selected\n" +
                "Select what you want to do\n" +
                "1. Show entry by key\n" +
                "2. Show all entries\n" +
                "3. Add entry to dictionary\n" +
                "4. Remove entry from dictionary\n" +
                "5. Back to Main Menu");

        switch (consoleInteractions.ask(console, scanner)) {
            case "1":
                consoleInteractions.say("Enter a key to search to");
                showWord(consoleInteractions.ask(console, scanner));
                break;

            case "2":
                showAllWords();
                break;

            case "3":
                consoleInteractions.say("Enter your dictionary entry using format: key-value1/value2/...");
                addWord(consoleInteractions.ask(console, scanner));
                break;

            case "4":
                consoleInteractions.say("Enter a Key to delete an entry");
                deleteWord(consoleInteractions.ask(console, scanner));
                break;
            case "5":
                setUIState(UIState.MAIN_MENU);
                break;
        }

    }

    private void showWord(String key) {
        Optional<GetWord> word = dictionaryServiceMap.get(currentDictionaryKey)
                .getWordByKey(key);
        word.ifPresent(w -> consoleInteractions.say(key + " " + w.toString()));
        if (word.isEmpty()) {
            consoleInteractions.say("Word not found");
        }
    }
    private void showAllWords() {
        List<Word> wordList = new ArrayList<>(dictionaryServiceMap.get(currentDictionaryKey).getAllWords());
        for (Word word: wordList){
            consoleInteractions.say(word.getKey() + " " + word.getValues());
        }
    }
    private void addWord(String entry) {
        String key;
        String[] keyPlusValue = entry.split("-");
        key = keyPlusValue[0];

        List<String> values = List.of(keyPlusValue[1].split("/"));
        dictionaryServiceMap.get(currentDictionaryKey).createWord(key, values);
        setDictionaryMap(dictionaryServiceMap);
    }
    private void deleteWord(String key) {
        dictionaryServiceMap.get(currentDictionaryKey).deleteEntryByKey(key);
    }
}
