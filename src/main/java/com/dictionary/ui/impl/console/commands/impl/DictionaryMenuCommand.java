package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.dao.impl.FileDictionary;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;
import com.dictionary.ui.impl.console.ConsoleUi;
import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.MenuInteractionState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;

public class DictionaryMenuCommand implements Command {
    DictionaryService dictionaryService;
    MenuInteractionState menuInteractionState;

    @Override
    public void execute(Console console, Scanner scanner) {
        setUIState(UIState.DICTIONARY_MENU);
        showCreatedDictionaryMenu();
        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            }
        }

        dictionaryMenuInteractions(menuInteractionState);
    }

    private void showCreatedDictionaryMenu() {
        int i = 1;
        consoleInteractions.say("Your dictionaries:\n+" +
                "1. RUS-ENG Dictionary\n" +
                "2. ENG-RUS Dictionary\n" +
                "3. Back to main menu");

        consoleInteractions.say("Select a dictionary to work with");


    }

    private void dictionaryMenuInteractions(MenuInteractionState menuInteractionState) {
        switch (menuInteractionState){
            case FIRST_OPTION:
                new DictionaryEditMenuCommand(new DictionaryService
                        (new FileDictionary("RUS-ENG"), new RusValidator(), new EngValidator()))
                        .execute(ConsoleUi.getConsole(), ConsoleUi.getScanner());
                break;
            case SECOND_OPTION:
                new DictionaryEditMenuCommand(new DictionaryService
                        (new FileDictionary("ENG-RUS"), new RusValidator(), new EngValidator()))
                        .execute(ConsoleUi.getConsole(), ConsoleUi.getScanner());
                break;
            case THIRD_OPTION:
                setUIState(UIState.MAIN_MENU);
                break;
            default:
                consoleInteractions.say("Unknown command");
                break;
        }


    }
}
