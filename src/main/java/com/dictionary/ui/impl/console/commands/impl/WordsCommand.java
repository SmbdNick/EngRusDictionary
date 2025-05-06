package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.service.SqlDictionaryService;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.MenuInteractionState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;
import static com.dictionary.ui.impl.console.ConsoleUi.getConsole;
import static com.dictionary.ui.impl.console.ConsoleUi.getScanner;

public class WordsCommand implements Command {
    MenuInteractionState menuInteractionState;
    SqlDictionaryService sqlDictionaryService = new SqlDictionaryService();

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.WORDS);

        consoleInteractions.say("""
                Please enter watchu wanna do:
                1. See all words
                2. Add new word
                3. Delete word
                4. Back to Main Menu""");

        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            }
        }

        switch (menuInteractionState) {
            case FIRST_OPTION:
                getAllWords();
                break;

            case SECOND_OPTION:
                insertNewWord(console, scanner);
                break;

            case THIRD_OPTION:
                deleteWord(console, scanner);
                break;

            case FOURTH_OPTION:
                setUIState(UIState.MAIN_MENU);

            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                break;
        }

    }

    private void getAllWords() {
        sqlDictionaryService.selectAllFrom("words");

        execute(getConsole(), getScanner());
    }

    private void insertNewWord(Console console, Scanner scanner) {
        consoleInteractions.say("What word you want to add?\n" +
                "Use the next pattern: word-language");
        String input = consoleInteractions.ask(console, scanner);
        String word = null;
        String language = null;
        try {
            String[] wordPlusLanguage = input.split("-");
            word = wordPlusLanguage[0];
            language = wordPlusLanguage[1];
        } catch (RuntimeException e) {
            consoleInteractions.say("You got fucked up, bruh! Try again!");
            execute(getConsole(), getScanner());
        }

        sqlDictionaryService.validate(language, word);
        sqlDictionaryService.insertIntoTable("words", "word,language", new String[]{word, language});

        execute(console, scanner);
    }

    private void deleteWord(Console console, Scanner scanner) {
        consoleInteractions.say("What word you want to delete?");
        String word = consoleInteractions.ask(console, scanner);
        sqlDictionaryService.deleteFromWhere("words", "word", word);

        execute(console, scanner);
    }
}
