package com.dictionary.ui.impl.console;

import com.dictionary.ui.api.Ui;

import java.io.Console;
import java.util.Scanner;

public class ConsoleUi implements Ui {
    private static final Scanner scanner = new Scanner(System.in);

    public static Scanner getScanner() {
        return scanner;
    }

    private static final Console console = System.console();

    public static Console getConsole() {
        return console;
    }

    @Override
    public void start() {
        ConsoleUIRunner consoleUIRunner = new ConsoleUIRunner(console, scanner);
        consoleUIRunner.run();
    }
}
