package com.dictionary.ui.impl.console;

import com.dictionary.service.DictionaryService;
import com.dictionary.ui.api.Ui;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUi implements Ui {

    private static Map<String, DictionaryService> dictionaryMap = new HashMap();

    public static Map<String, DictionaryService> getDictionaryMap() {
        return dictionaryMap;
    }

    public static void setDictionaryMap(Map<String, DictionaryService> dictionaryMap) {
        ConsoleUi.dictionaryMap = dictionaryMap;
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
        ConsoleUIRunner consoleUIRunner = new ConsoleUIRunner(console, scanner);
        consoleUIRunner.run();
    }
}
