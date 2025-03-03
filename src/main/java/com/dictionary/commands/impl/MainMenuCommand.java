package com.dictionary.commands.impl;

import com.dictionary.commands.api.Command;
import com.dictionary.service.DictionaryService;
import com.dictionary.ui.api.UIState;

import java.io.Console;
import java.util.Map;
import java.util.Scanner;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;
import static com.dictionary.ui.impl.ConsoleUi.setuIstate;

public class MainMenuCommand implements Command {
    @Override
    public void doCommand(UIState uiState, Console console, Scanner scanner) {
        CreationMenuCommand creationMenuCommand = new CreationMenuCommand();

        uiState = UIState.MAIN_MENU;
        say("Welcome to Dictionary Creator 3000\n" +
                "Please input your command\n" +
                "1. Create Dictionary\n" +
                "2. Existing Dictionary Menu\n");


        switch (ask(console, scanner)) {
            case "1":
                uiState = UIState.CREATION_MENU;
                setuIstate(uiState);
                break;

            case "2":
                uiState = UIState.DICTIONARY_MENU;
                setuIstate(uiState);
                break;

            default:
                say("Unknown command, please reenter your command");
                setuIstate(uiState);
                break;

        }

    }
}
