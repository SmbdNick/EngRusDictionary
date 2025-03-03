package com.dictionary.commands.impl;

import com.dictionary.commands.api.Command;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;
import static com.dictionary.ui.impl.ConsoleUi.getCurrentDictionaryKey;
import static com.dictionary.ui.impl.ConsoleUi.setuIstate;

public class DictionaryEditMenuCommand implements Command {
    @Override
    public void doCommand(UIState uiState, Console console, Scanner scanner) {
        uiState = UIState.DICTIONARY_EDIT_MENU;
        String currentDictionaryKey = getCurrentDictionaryKey();

        say(currentDictionaryKey + " dictionary selected\n" +
                "Select what you want to do\n" +
                "1. Show entry by key\n" +
                "2. Show all entries\n" +
                "3. Add entry to dictionary\n" +
                "4. Remove entry from dictionary");

        switch (ask(console, scanner)) {
            case "1":
                say("Enter a key to search to");
//                showWord(ask(console, scanner));
                setuIstate(uiState);
                break;

            case "2":
//                showAllWords();
                setuIstate(uiState);
                break;

            case "3":
                say("Enter your dictionary entry using format: key-value1/value2/...");
//                addWord(ask(console, scanner));
                setuIstate(uiState);
                break;
        }

    }
}
