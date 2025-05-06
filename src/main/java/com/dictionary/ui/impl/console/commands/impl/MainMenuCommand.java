package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.MenuInteractionState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;

public class MainMenuCommand implements Command {
    MenuInteractionState menuInteractionState;

    @Override
    public void execute(Console console, Scanner scanner) {

        setUIState(UIState.MAIN_MENU);
        consoleInteractions.say("""
                Welcome to Dictionary 3000
                Please input your command
                1. Modify Words Data
                2. Modify Dictionaries Data
                3. Modify Translations Data
                4. Exit program""");

        String com = consoleInteractions.ask(console, scanner);
        for (MenuInteractionState menuInteractionState : MenuInteractionState.values()) {
            if (menuInteractionState.getFirst().equals(com) || menuInteractionState.getSecond().equals(com)) {
                this.menuInteractionState = menuInteractionState;
                break;
            }
        }

        switch (menuInteractionState) {
            case FIRST_OPTION:
                setUIState(UIState.WORDS);
                break;

            case SECOND_OPTION:
                setUIState(UIState.DICTIONARIES);
                break;

            case THIRD_OPTION:
                setUIState(UIState.TRANSLATIONS);
                break;
            case FOURTH_OPTION:
                System.exit(0);
            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                break;
        }
    }
}
