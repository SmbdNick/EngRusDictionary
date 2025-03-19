package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.service.DictionaryService;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.MenuInteractionState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.getConsole;
import static com.dictionary.ui.impl.console.ConsoleUi.getScanner;

public class DictionaryEditMenuCommand implements Command {

    MenuInteractionState menuInteractionState;
    DictionaryService dictionaryService;

    public DictionaryEditMenuCommand(DictionaryService dictionaryService) {
        this.dictionaryService = dictionaryService;
    }

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.DICTIONARY_EDIT_MENU);


        consoleInteractions.say("Dictionary selected\n" +
                "Select what you want to do\n" +
                "1. Show entry by key\n" +
                "2. Show all entries\n" +
                "3. Add entry to dictionary\n" +
                "4. Remove entry from dictionary\n" +
                "5. Back to Main Menu");

        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            } else {
                execute(console, scanner);
            }
        }

        switch (menuInteractionState) {
            case FIRST_OPTION:
                showEntryByKey(console, scanner);
                break;
            case SECOND_OPTION:
                showAllWords();
                break;
            case THIRD_OPTION:
                addEntryToDictionary(console, scanner);
                break;
            case FOURTH_OPTION:
                removeEntryFromDictionary(console, scanner);
                break;
            case FIFTH_OPTION:
                setUIState(UIState.MAIN_MENU);
                break;
        }

    }

    private void removeEntryFromDictionary(Console console, Scanner scanner) {
        consoleInteractions.say("Enter a Key to delete an entry");
        deleteWord(consoleInteractions.ask(console, scanner));
        execute(console, scanner);
    }

    private void addEntryToDictionary(Console console, Scanner scanner) {
        consoleInteractions.say("Enter your dictionary entry using format: key-value1,value2,...");
        addWord(consoleInteractions.ask(console, scanner));
        execute(console, scanner);
    }

    private void showEntryByKey(Console console, Scanner scanner) {
        consoleInteractions.say("Enter a key to search to");
        showWord(consoleInteractions.ask(console, scanner));
        execute(console, scanner);
    }

    private void showWord(String key) {
        Optional<GetWord> word = dictionaryService
                .getWordByKey(key);
        word.ifPresent(w -> consoleInteractions.say(key + " " + w));
        if (word.isEmpty()) {
            consoleInteractions.say("Word not found");
        }
        execute(getConsole(), getScanner());
    }

    private void showAllWords() {
        List<Word> wordList = new ArrayList<>(dictionaryService.getAllWords());
        for (Word word : wordList) {
            consoleInteractions.say(word.getKey() + " " + word.getValues());
        }
        execute(getConsole(), getScanner());
    }

    private void addWord(String entry) {
        String key;

        try {
            String[] keyPlusValue = entry.split("-");
            {
                key = keyPlusValue[0];

                List<String> values = List.of(keyPlusValue[1].split(","));
                dictionaryService.createWord(key, values);

                execute(getConsole(), getScanner());
            }
        } catch (RuntimeException e) {
            consoleInteractions.say("You got fucked up, bruh! Try again!");
            execute(getConsole(), getScanner());
        }

    }

    private void deleteWord(String key) {
        dictionaryService.deleteEntryByKey(key);

        execute(getConsole(), getScanner());
    }
}
