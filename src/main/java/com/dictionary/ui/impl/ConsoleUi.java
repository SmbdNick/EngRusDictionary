package com.dictionary.ui.impl;

import com.dictionary.commands.api.CommandFactory;
import com.dictionary.commands.factory.CreationMenuFactory;
import com.dictionary.commands.factory.DictionaryEditFactory;
import com.dictionary.commands.factory.DictionaryMenuFactory;
import com.dictionary.commands.factory.MainMenuFactory;
import com.dictionary.dao.impl.InMemoryDictionary;
import com.dictionary.dto.GetWord;
import com.dictionary.model.Word;
import com.dictionary.service.DictionaryService;
import com.dictionary.service.validator.impl.EngValidator;
import com.dictionary.service.validator.impl.RusValidator;
import com.dictionary.ui.api.UIState;
import com.dictionary.ui.api.Ui;

import java.io.Console;
import java.util.*;

import static com.dictionary.commands.Ask.ask;
import static com.dictionary.commands.Say.say;

public class ConsoleUi implements Ui {

    private static Map<String, DictionaryService> dictionaryMap = new HashMap();

    public static Map<String, DictionaryService> getDictionaryMap() {
        return dictionaryMap;
    }

    public static void setDictionaryMap(Map<String, DictionaryService> dictionaryMap) {
        ConsoleUi.dictionaryMap = dictionaryMap;
    }


    private static UIState uIstate = UIState.MAIN_MENU;

    public static UIState getUIState() {
        return uIstate;
    }

    public static void setUIState(UIState uIstate) {
        ConsoleUi.uIstate = uIstate;
    }

    private static String currentDictionaryKey;

    public static String getCurrentDictionaryKey() {
        return currentDictionaryKey;
    }

    public static void setCurrentDictionaryKey(String currentDictionaryKey) {
        ConsoleUi.currentDictionaryKey = currentDictionaryKey;
    }

    private final Scanner scanner = new Scanner(System.in);

    public Scanner getScanner() {
        return scanner;
    }


    private final Console console = System.console();

    public Console getConsole() {
        return console;
    }

    @Override
    public void start() {
        CommandFactory commandFactory;

        while (true) {
            switch (uIstate) {
                case MAIN_MENU:
                    commandFactory = new MainMenuFactory();
                    commandFactory.command(uIstate, console, scanner);
                    break;
                case DICTIONARY_MENU:
                    commandFactory = new DictionaryMenuFactory();
                    commandFactory.command(uIstate, console, scanner);
                    break;
                case CREATION_MENU:
                    commandFactory = new CreationMenuFactory();
                    commandFactory.command(uIstate, console, scanner);
                    break;
                case DICTIONARY_EDIT_MENU:
                    commandFactory = new DictionaryEditFactory();
                    commandFactory.command(uIstate, console, scanner);
            }
        }
    }
}
