package com.dictionary.ui.impl.console;

import com.dictionary.ui.impl.console.commands.api.CommandFactory;
import com.dictionary.ui.impl.console.commands.factory.CreationMenuFactory;
import com.dictionary.ui.impl.console.commands.factory.DictionaryEditFactory;
import com.dictionary.ui.impl.console.commands.factory.DictionaryMenuFactory;
import com.dictionary.ui.impl.console.commands.factory.MainMenuFactory;

import java.io.Console;
import java.util.Scanner;

public class ConsoleUIRunner {
    private final Console console;
    private final Scanner scanner;
    private static UIState uIstate = UIState.MAIN_MENU;
    public static UIState getUIState() {
        return uIstate;
    }
    public static void setUIState(UIState uIstate) {
        ConsoleUIRunner.uIstate = uIstate;
    }
    public void run() {
        CommandFactory commandFactory;

        while (true) {
            switch (uIstate) {
                case MAIN_MENU:
                    commandFactory = new MainMenuFactory();
                    commandFactory.construct(uIstate, console, scanner);
                    break;
                case DICTIONARY_MENU:
                    commandFactory = new DictionaryMenuFactory();
                    commandFactory.construct(uIstate, console, scanner);
                    break;
                case CREATION_MENU:
                    commandFactory = new CreationMenuFactory();
                    commandFactory.construct(uIstate, console, scanner);
                    break;
                case DICTIONARY_EDIT_MENU:
                    commandFactory = new DictionaryEditFactory();
                    commandFactory.construct(uIstate, console, scanner);
            }
        }
    }

    public ConsoleUIRunner(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }
}
