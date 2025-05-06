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

public class DictionariesCommand implements Command {
    MenuInteractionState menuInteractionState;
    SqlDictionaryService sqlDictionaryService;

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.DICTIONARIES);

        consoleInteractions.say("""
                Please enter watchu wanna do:
                1. See all dictionaries
                2. Add new dictionary
                3. Delete dictionary
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
                getAllDictionaries();
                break;

            case SECOND_OPTION:
                insertNewDictionary(console, scanner);
                break;

            case THIRD_OPTION:
                deleteDictionary(console, scanner);
                break;

            case FOURTH_OPTION:
                setUIState(UIState.MAIN_MENU);

            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                break;
        }
    }

    private void getAllDictionaries() {
        sqlDictionaryService = new SqlDictionaryService();
        sqlDictionaryService.selectAllFrom("dictionaries");

        execute(getConsole(), getScanner());
    }

    private void insertNewDictionary(Console console, Scanner scanner) {
        consoleInteractions.say("What dictionary you want to add?");
        String input = consoleInteractions.ask(console, scanner);

        sqlDictionaryService.insertIntoTable("dictionaries", "dictionary", input);

        execute(console, scanner);
    }

    private void deleteDictionary(Console console, Scanner scanner) {
        consoleInteractions.say("What dictionary you want to delete?");
        String word = consoleInteractions.ask(console, scanner);
        sqlDictionaryService.deleteFromWhere("dictionaries", "dictionary", word);

        execute(console, scanner);
    }
}
