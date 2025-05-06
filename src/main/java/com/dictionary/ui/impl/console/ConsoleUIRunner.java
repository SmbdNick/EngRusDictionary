package com.dictionary.ui.impl.console;

import com.dictionary.ui.impl.console.commands.api.Command;
import com.dictionary.ui.impl.console.commands.impl.CommandFactory;

import java.io.Console;
import java.util.Scanner;

public class ConsoleUIRunner {
    private final Console console;
    private final Scanner scanner;
    private static UIState uIstate = UIState.MAIN_MENU;

    public static void setUIState(UIState uIstate) {
        ConsoleUIRunner.uIstate = uIstate;
    }

    public void run() {
        CommandFactory commandFactory = new CommandFactory();
        Command command;

        while (true) {
             command = commandFactory.createCommand(uIstate);
             command.execute(console, scanner);
        }
    }

    public ConsoleUIRunner(Console console, Scanner scanner) {
        this.console = console;
        this.scanner = scanner;
    }
}
