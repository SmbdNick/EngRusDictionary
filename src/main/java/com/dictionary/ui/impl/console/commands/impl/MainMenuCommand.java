package com.dictionary.ui.impl.console.commands.impl;

import com.dictionary.ui.impl.console.UIState;
import com.dictionary.ui.impl.console.commands.api.Command;

import java.io.Console;
import java.util.Scanner;

import static com.dictionary.ui.impl.console.ConsoleUIRunner.setUIState;

public class MainMenuCommand implements Command {
    @Override
    public void execute(UIState uiState, Console console, Scanner scanner) {
        CreationMenuCommand creationMenuCommand = new CreationMenuCommand();

        uiState = UIState.MAIN_MENU;
        consoleInteractions.say("Welcome to Dictionary Creator 3000\n" +
                "Please input your command\n" +
                "1. Create Dictionary\n" +
                "2. Existing Dictionary Menu\n");


        switch (consoleInteractions.ask(console, scanner)) {
            case "1":
                uiState = UIState.CREATION_MENU;
                setUIState(uiState);
                break;

            case "2":
                uiState = UIState.DICTIONARY_MENU;
                setUIState(uiState);
                break;

            default:
                consoleInteractions.say("Unknown command, please reenter your command");
                setUIState(uiState);
                break;

        }

    }
}
