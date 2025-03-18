package com.dictionary.ui.impl.console.commands;

import java.io.Console;
import java.util.Scanner;

public class ConsoleInteractions {
    public String ask(String outputMessage, Console console, Scanner scanner) {
        say(outputMessage);
        String command = null;
        try {
            if (console == null) {
                command = scanner.nextLine();
            } else {
                command = console.readLine();

            }
        } catch (Exception e) {
        }
        return command;
    }

    public String ask(Console console, Scanner scanner) {
        return ask("->", console, scanner);
    }

    public void say(String message) {
        System.out.println(message);
    }

}
