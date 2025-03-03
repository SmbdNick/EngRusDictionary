package com.dictionary;

import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.factory.*;
import com.dictionary.ui.impl.ConsoleUi;

class Main {
    public static void main(String[] args) {
        ConsoleUi consoleUi = new ConsoleUi();
        CommandFactory commandFactory;

        while (true) {
            switch (consoleUi.getuIstate()) {
                case MAIN_MENU:
                    commandFactory = new MainMenuFactory();
                    commandFactory.command(consoleUi.getuIstate(), consoleUi.getConsole(), consoleUi.getScanner());
                    break;
                case DICTIONARY_MENU:
                    commandFactory = new DictionaryMenuFactory();
                    commandFactory.command(consoleUi.getuIstate(), consoleUi.getConsole(), consoleUi.getScanner());
                    break;
                case CREATION_MENU:
                    commandFactory = new CreationMenuFactory();
                    commandFactory.command(consoleUi.getuIstate(), consoleUi.getConsole(), consoleUi.getScanner());
                    break;
                case DICTIONARY_EDIT_MENU:
                    commandFactory = new DictionaryEditFactory();
                    commandFactory.command(consoleUi.getuIstate(), consoleUi.getConsole(), consoleUi.getScanner());
            }
        }
    }
}
