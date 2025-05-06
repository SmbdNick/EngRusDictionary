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

public class TranslationsCommand implements Command {
    MenuInteractionState menuInteractionState;
    SqlDictionaryService sqlDictionaryService = new SqlDictionaryService();

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.TRANSLATIONS);

        consoleInteractions.say("""
                Please enter watchu gonna do:
                1. See all translations
                2. See translations by dictionary
                3. Add new translation
                4. Delete translation
                5. Back to Main Menu""");

        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            }
        }

        switch (menuInteractionState) {
            case FIRST_OPTION:
                getAllTranslations();
                break;

            case SECOND_OPTION:
                getTranslationsByDictionary(console, scanner);
                break;

            case THIRD_OPTION:
                addNewTranslation(console, scanner);
                break;

            case FOURTH_OPTION:
                deleteTranslation(console, scanner);
                break;

            case FIFTH_OPTION:
                setUIState(UIState.MAIN_MENU);
                break;

            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                break;
        }

    }

    private void getAllTranslations() {
        sqlDictionaryService.selectAllFrom("dictionary_translations");

        execute(getConsole(), getScanner());
    }

    private void getTranslationsByDictionary(Console console, Scanner scanner) {
        consoleInteractions.say("Enter what dictionary you want to see?");
        String dictionary = consoleInteractions.ask(console, scanner);

        sqlDictionaryService.selectColumnsFromWhere("*", "dictionary_translations", "dictionary", dictionary);

        execute(console, scanner);
    }

    private void addNewTranslation(Console console, Scanner scanner) {
        consoleInteractions.say("Enter new translation using format: word/translation/dictionary");
        String input = consoleInteractions.ask(console, scanner);
        String word = null;
        String translation = null;
        String dictionary = null;

        try {
            String[] engPlusRus = input.split("/");
            word = engPlusRus[0];
            translation = engPlusRus[1];
            dictionary = engPlusRus[2];
        } catch (RuntimeException e) {
            consoleInteractions.say("You got fucked up, bruh! Try again!");
            execute(getConsole(), getScanner());
        }

        sqlDictionaryService.insertNewTranslation(word, translation, dictionary);

        execute(console, scanner);
    }

    private void deleteTranslation(Console console, Scanner scanner) {
        consoleInteractions.say("What translation you want to delete?\n" +
                "Please follow the next format: key/translation/dictionary");
        String input = consoleInteractions.ask(console, scanner);
        String key = null;
        String translation = null;
        String dictionary = null;

        try {
            String[] keyDictionary = input.split("/");
            key = keyDictionary[0];
            translation = keyDictionary[1];
            dictionary = keyDictionary[2];
        } catch (RuntimeException e) {
            consoleInteractions.say("You got fucked up, bruh! Try again!");
            execute(getConsole(), getScanner());
        }

        sqlDictionaryService.deleteTranslation(key, translation, dictionary);
        execute(console, scanner);
    }
}

